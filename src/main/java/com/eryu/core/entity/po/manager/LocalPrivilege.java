package com.eryu.core.entity.po.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限表
 */
@Getter
@Setter
@Entity
@Table(name = "T_SECURITY_PRIVILEGE")
@NoArgsConstructor
public class LocalPrivilege extends AbstractEntity {

    /**
     * 权限名称
     */
    @Column(name = "NAME", length = 32, nullable = false)
    private String name;

    /**
     * 对应的地址
     */
    @Column(name = "PATH", length = 64)
    private String path;

    /**
     * 模块
     */
    @Column(name = "MODEL", length = 32, nullable = false)
    private String model;

    /**
     * 页面
     */
    @Column(name = "PAGE", length = 32)
    private String page;

    /**
     * 权限点
     */
    @Column(name = "POINT", length = 32)
    private String point;

    /**
     * 是否根权限
     */
    @Column(name = "IS_ROOT")
    private Boolean isRoot = false;

    /**
     * 父权限
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PARENT_ID")
    @JsonIgnore
    private LocalPrivilege parent;

    /**
     * 子权限
     * 及时加载
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<LocalPrivilege> children;

    /**
     * 关联角色
     */
    @ManyToMany(mappedBy = "privileges", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private Set<LocalRole> roles;


    public LocalPrivilege(String name, String path, String model, String page, String point) {
        this(name, path, model, page, point, null);
    }


    public LocalPrivilege(String name, String path, String model, String page, String point, LocalPrivilege parent) {
        this.name = name;
        this.path = path;
        this.model = model;
        this.page = page;
        this.point = point;
        this.isRoot = parent == null;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalPrivilege)) return false;
        if (!super.equals(o)) return false;

        LocalPrivilege that = (LocalPrivilege) o;

        return (getName() != null ? getName().equals(that.getName()) : that.getName() == null) && (getPath() != null ? getPath().equals(that.getPath()) : that.getPath() == null) && (getModel() != null ? getModel().equals(that.getModel()) : that.getModel() == null) && (getPage() != null ? getPage().equals(that.getPage()) : that.getPage() == null) && (getPoint() != null ? getPoint().equals(that.getPoint()) : that.getPoint() == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPath() != null ? getPath().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getPage() != null ? getPage().hashCode() : 0);
        result = 31 * result + (getPoint() != null ? getPoint().hashCode() : 0);
        return result;
    }

    /**
     * 获取权限编码
     *
     * @return 廯编码
     */
    private String getPrivilege() {
        StringBuilder stringBuilder = new StringBuilder();
        //模块
        stringBuilder.append(getModel().toUpperCase());
        //页面
        if (StringUtils.hasText(getPage()))
            stringBuilder.append("_").append(getPage().toUpperCase());
        //点
        if (StringUtils.hasText(getPoint()))
            stringBuilder.append("_").append(getPoint().toUpperCase());
        return stringBuilder.toString();
    }

    /**
     * 获取权限编码
     *
     * @return 廯编码
     */
    public String getRolePrivilege() {
        return "ROLE_" + getPrivilege();
    }

    /**
     * 获取视图名称
     */
    public String getViewName() {
        return getModel() + "/" + getPage();
    }
}
