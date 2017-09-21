package com.eryu.common.security;

import com.eryu.core.entity.po.manager.LocalRole;
import com.eryu.core.entity.po.manager.LocalUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SecurityUser extends LocalUser implements UserDetails {


    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     *
     * @param localUser 用户信息
     */
    public SecurityUser(LocalUser localUser) {
        if (localUser != null) {
            this.setId(localUser.getId());
            this.setName(localUser.getName());
            this.setPassword(localUser.getPassword());
            this.setRoles(localUser.getRoles());
        }
    }

    /**
     * 用户的权限
     *
     * @return 用户的权限
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<LocalRole> userRoles = this.getRoles();
        if (userRoles != null) {
            userRoles.forEach(role -> role.getPrivileges().forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege.getRolePrivilege()))));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
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

}