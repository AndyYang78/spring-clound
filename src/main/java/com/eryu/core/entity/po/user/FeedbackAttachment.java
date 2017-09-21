package com.eryu.core.entity.po.user;

import com.eryu.core.entity.po.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 产品反馈照片
 * <p>
 * Created by yangtao on 2017/7/6.
 */
@Entity
@Table(name = "T_FEEDBACK_ATTACHMENT")
@Getter
@Setter
public class FeedbackAttachment extends AbstractEntity {

    /**
     * 附件地址
     */
    @Column(name = "URL", columnDefinition = ("VARCHAR(64) NOT NULL COMMENT '附件地址'"))
    private String url;

    /**
     * 文件类型
     */
    @Column(name = "TYPE", columnDefinition = "VARCHAR(12) DEFAULT NULL COMMENT '文件类型'")
    private String type;

    /**
     * 反馈
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "FEEDBACK_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '反馈ID'")
    @JsonIgnore
    private Feedback feedback;
}
