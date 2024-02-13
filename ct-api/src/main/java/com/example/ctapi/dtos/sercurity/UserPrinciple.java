package com.example.ctapi.dtos.sercurity;

import com.example.ctcommon.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;


    private String email;
    private String username;

    private String phone;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private String agencyId;

    private String companyId;

    private String timezone;

    private Role role;

    public UserPrinciple() {
    }

    public UserPrinciple(String email, String phone, String password, String agencyId, String companyId,
                         String timezone, Collection<? extends GrantedAuthority> authorities, Role role) {
        this.username = email;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.agencyId = agencyId;
        this.companyId = companyId;
        this.timezone = timezone;
        this.authorities = authorities;
        this.role = role;
    }



    public static UserPrinciple build(UserDto user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> permissions = user.getPermissions();

        // Add userModel as authority
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        if (permissions != null) {
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + permission));
            }
        }

        return new UserPrinciple(
                user.getEmail(),
                user.getPhone(),
                null,
                user.getAgencyId(),
                user.getCompanyId(),
                user.getTimeZone(),
                authorities,
                user.getRole()
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(username, user.username);
    }
}
