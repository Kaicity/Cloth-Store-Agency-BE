package com.example.ctstart.controllers;

import com.example.ctapi.dtos.response.ResponseDto;
import com.example.ctapi.dtos.sercurity.JwtAuthenticationResponse;
import com.example.ctapi.dtos.sercurity.SigninRequest;
import com.example.ctapi.dtos.sercurity.UserDto;
import com.example.ctapi.dtos.sercurity.UserPrinciple;
import com.example.ctapi.services.JWTService;
import com.example.ctcommon.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticatonController {
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
//    @PostMapping("/signup")
//    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
//        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
//
//    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SigninRequest signinRequest, HttpServletRequest request){
        UserDto userDto = new UserDto();
        userDto.setId("123");
        userDto.setFullName("Ronaldomessi");
        userDto.setPhone("0123123123");
        userDto.setCompanyId("Com1");
        userDto.setEmail(signinRequest.getEmail());
        userDto.setRole(Role.ADMIN);



        UserPrinciple userDetail = UserPrinciple.build(userDto);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetail, null, userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(authentication);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);

        return ResponseEntity.ok(jwtAuthenticationResponse);

    }
//    @PostMapping("/refresh")
//    public ResponseEntity<JwtAuthenticationResponse>refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
//        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
//
//    }

    @PostMapping("/getPassword")
    public ResponseEntity<?>getPassword(){
        String password = "admin";
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password: " + encodedPassword);
          return ResponseEntity.ok(new ResponseDto(List.of("get pass ok"),
                HttpStatus.OK.value(), encodedPassword));
    }


}
