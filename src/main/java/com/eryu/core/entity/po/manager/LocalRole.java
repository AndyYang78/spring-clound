package com.eryu.core.entity.po.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * 角色表
 */
@Getter
@Setter
@Entity
@Table(name = "T_SECURITY_ROLE")
public class LocalRole extends AbstractEntity {

    /**
     * 角色名
     */
    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    /**
     * 角色所对应的权限
     **/
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "T_SECURITY_ROLE_PRIVILEGE", joinColumns = {@JoinColumn(name = "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID")})
    private Set<LocalPrivilege> privileges;

    /**
     * 关联用户
     */
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<LocalUser> users;
}