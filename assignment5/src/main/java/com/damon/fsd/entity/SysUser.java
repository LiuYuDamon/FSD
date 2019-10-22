package com.damon.fsd.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
@Data
public class SysUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 4359709211352400087L;

    private String username;
    private String password;
    private String name;
    @Email(message = "email error")
    private String email;
    private String role;
    private String oldPassword;
    private String newPassword;
    private String kaptcha;
    private List<? extends GrantedAuthority> authorities;

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
}
