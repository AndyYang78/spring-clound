package com.eryu.core.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类似的抽象类似
 * <p>
 * Created by lihui on 2017/6/19.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID", updatable = false, columnDefinition = "VARCHAR(32) NOT NULL COMMENT '主键'")
    private String id;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", updatable = false, columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 实体保存前
     */
    @PrePersist
    protected void prePersist() {
        this.setCreateTime(new Date());
        this.setUpdateTime(new Date());
    }

    /**
     * 实体更新前
     */
    @PreUpdate
    protected void preUpdate() {
        this.setUpdateTime(new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;

        AbstractEntity that = (AbstractEntity) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
