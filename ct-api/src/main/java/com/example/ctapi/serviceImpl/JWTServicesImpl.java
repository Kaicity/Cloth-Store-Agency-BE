package com.example.ctapi.serviceImpl;

import com.example.ctapi.dtos.sercurity.UserDto;
import com.example.ctapi.dtos.sercurity.UserPrinciple;
import com.example.ctapi.services.JWTService;
import com.example.ctcommon.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JWTServicesImpl implements JWTService {

    @Override
    public String generateToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256) // Changed to HS256
                .claim("email", userPrincipal.getEmail())
                .claim("phone", userPrincipal.getPhone())
                .claim("Role", userPrincipal.getRole())
                .claim("agencyId", userPrincipal.getAgencyId())
                .claim("companyId", userPrincipal.getCompanyId())
                .claim("timeZone", userPrincipal.getTimezone())
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Object> extracClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extracClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256) // Changed to HS256
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }




    // ??? chữ t dau:))
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    private Key getSiginKey() {
        byte[] key = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public Authentication getAllInfomationByToken(String token) {
        // Giải mã token để lấy thông tin Claims
        Claims claims = Jwts.parser()
                .setSigningKey(getSiginKey())
                .parseClaimsJws(token)
                .getBody();

        // Lấy username từ Claims
        String username = claims.getSubject();

        // Xây dựng đối tượng UserPrinciple từ thông tin trong token

        UserDto userDto = new UserDto();
        userDto.setFullName(username);
        userDto.setEmail((String) claims.get("email"));
        userDto.setPhone((String) claims.get("phone"));
        userDto.setRole(Role.valueOf((String) claims.get("Role")));
        userDto.setAgencyId((String) claims.get("agencyId"));
        userDto.setCompanyId((String) claims.get("companyId"));
        userDto.setTimeZone((String) claims.get("timeZone"));
        UserPrinciple userPrinciple = UserPrinciple.build(userDto);
        // Tạo đối tượng UserDetails từ UserPrinciple
        UserDetails userDetails = userPrinciple;

        // Tạo đối tượng Authentication từ UserDetails
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
