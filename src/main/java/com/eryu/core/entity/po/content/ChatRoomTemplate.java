package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 房间模板实体
 * Created by yangtao on 2017/8/24.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_CHATROOM_TEMPLATE")
public class ChatRoomTemplate extends AbstractEntity {

    @Transient
    public static final int AVAILABLE = 1;

    @Transient
    public static final int UNAVAILABLE = 0;

    /**
     * 模板名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '房间模板名称'")
    private String name;

    /**
     * 模板代码
     */
    @Column(name = "CODE", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '房间模板代码'")
    private String code;

    /**
     * 状态
     */
    @Column(name = "STATE", columnDefinition = "INT(4) COMMENT '状态，1为启用'")
    private Integer state = UNAVAILABLE;

    /**
     * 模板下的房间类型(标签)
     */
    @OneToMany(mappedBy = "chatRoomTemplate", cascade = {CascadeType.MERGE})
    @JsonIgnore
    private List<ChatRoomType> chatRoomTypes;

}