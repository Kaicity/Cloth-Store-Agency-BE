package com.example.ctapi.dtos.sercurity;

import com.example.ctcommon.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String fullName;
    private String phone;
    private String agencyId;
    private String companyId;
    private Boolean isGlobalAdmin;
    private List<String> permissions;
    private Role role;
    private String timeZone;
}
