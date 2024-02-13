package com.example.ctstart.configuration.jwt;


import com.example.ctapi.dtos.sercurity.UserPrinciple;
import com.example.ctapi.services.JWTService;
import com.example.ctcommon.enums.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        final String jwt;
        final String userEmail;
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwt);
        Authentication fullDetails = jwtService.getAllInfomationByToken(jwt);
        UserDetails userDetails = new UserPrinciple();
        System.out.println(fullDetails.getAuthorities().toString());
        Optional<? extends GrantedAuthority> firstAuthorityOptional = fullDetails.getAuthorities().stream().findFirst();
        if (firstAuthorityOptional.isPresent()) {
            GrantedAuthority firstAuthority = firstAuthorityOptional.get();
            System.out.println("Quyền đầu tiên: " + Role.ADMIN.name().equals(firstAuthority.getAuthority()));
        }
        boolean check = Role.ADMIN.name() == fullDetails.getAuthorities().toString();

        if (fullDetails != null && fullDetails.getPrincipal() instanceof UserDetails) {
            userDetails = (UserDetails) fullDetails.getPrincipal();
        }

        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetailsService userDetailsService = new UserDetailsService() {
//                @Override
//                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                    User user = new User();
//                    user.setEmail(username);
//                    user.setPassword(userDetails.getPassword());
//                    return user;
//                }
//            };
       //     UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
           if (jwtService.isTokenValid(jwt,userDetails)){
               SecurityContext securityContext= SecurityContextHolder.createEmptyContext();
               UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(
                       userDetails,null,userDetails.getAuthorities()
               );
             token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             securityContext.setAuthentication(token);
             SecurityContextHolder.setContext(securityContext);
           }

        }
        filterChain.doFilter(request,response);
    }
}
