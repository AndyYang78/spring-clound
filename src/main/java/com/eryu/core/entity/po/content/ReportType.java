package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 房间举报类型实体
 * Created by yangtao on 2017/7/5.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_REPORT_TYPE")
public class ReportType extends AbstractEntity {

    @Transient
    public static final int AVAILABLE = 1;
    @Transient
    public static final int UNAVAILABLE = 0;
    @Transient
    public static final int DELETED = -1;

    /**
     * 举报类型名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '房间类型名称'")
    private String name;

    /**
     * 状态
     */
    @Column(name = "STATE", columnDefinition = "INT(4) COMMENT '状态，-1删除，0停用，1为启用'")
    private Integer state = UNAVAILABLE;

}