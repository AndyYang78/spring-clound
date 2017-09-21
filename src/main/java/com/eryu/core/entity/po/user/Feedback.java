package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 产品反馈
 * <p>
 * Created by yangtao on 2017/7/4.
 */
@Entity
@Table(name = "T_FEEDBACK")
@Setter
@Getter
public class Feedback extends AbstractEntity {

    /**
     * App版本号
     */
    @Column(name = "APP_VERSION", updatable = false, columnDefinition = "VARCHAR(16) NOT NULL COMMENT 'App版本号'")
    private String appVersion;

    /**
     * 手机品牌
     */
    @Column(name = "BRAND", updatable = false, columnDefinition = "VARCHAR(16) NOT NULL COMMENT '手机品牌'")
    private String brand;

    /**
     * 手机设备类型
     */
    @Column(name = "DEVICE", updatable = false, columnDefinition = "VARCHAR(32) NOT NULL COMMENT '手机设备类型'")
    private String device;

    /**
     * 操作系统
     */
    @Column(name = "OS", updatable = false, columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统'")
    private String os;

    /**
     * 操作系统版本号
     */
    @Column(name = "OS_VERSION", updatable = false, columnDefinition = "VARCHAR(16) DEFAULT NULL COMMENT '操作系统版本号'")
    private String osVersion;

    /**
     * 反馈内容
     */
    @Column(name = "CONTENT", columnDefinition = "VARCHAR(256) DEFAULT NULL COMMENT '反馈内容'")
    private String content;

    /**
     * 处理状态(0:未处理, 1:以处理)
     */
    @Column(name = "STATUS", columnDefinition = "TINYINT(1) NOT NULL DEFAULT 0 COMMENT '处理状态(0:未处理, 1:以处理)'")
    private Integer status = 0;

    /**
     * 关联用户
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "USER_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '用户ID'")
    private User user;

    /**
     * 附件
     */
    @OneToMany(mappedBy = "feedback", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<FeedbackAttachment> attachments;
}
