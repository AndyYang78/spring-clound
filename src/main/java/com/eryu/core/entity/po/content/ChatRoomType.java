package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 房间类型实体
 * Created by yangtao on 2017/7/5.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_CHATROOM_TYPE")
public class ChatRoomType extends AbstractEntity {

    @Transient
    public static final int AVAILABLE = 1;
    @Transient
    public static final int UNAVAILABLE = 0;

    /**
     * 类型名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '房间类型名称'")
    private String name;

    /**
     * 配置的颜色
     */
    @Column(name = "COLOR", columnDefinition = "VARCHAR(16) NOT NULL COMMENT '房间类型名称'")
    private String color;

    /**
     * 状态
     */
    @Column(name = "STATE", columnDefinition = "INT(4) COMMENT '状态，1为启用'")
    private Integer state;

    /**
     * 属于哪个模板
     */
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "TEMPLATE_ID", columnDefinition = "VARCHAR(32) COMMENT '所属模板'")
    private ChatRoomTemplate chatRoomTemplate;

}
