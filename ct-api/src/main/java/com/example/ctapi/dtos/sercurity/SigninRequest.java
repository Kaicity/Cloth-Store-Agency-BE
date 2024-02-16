package com.example.ctapi.dtos.sercurity;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
