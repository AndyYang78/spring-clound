package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 房间举报信息实体
 * Created by yangtao on 2017/7/5.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_REPORT_CHATROOM")
public class ReportChatRoom extends AbstractEntity {

    //已删除
    @Transient
    public static final int DELETED = -1;
    //已处理
    @Transient
    public static final int COMPLETE = 1;
    //新建
    @Transient
    public static final int NEW = 999;

    /**
     * 举报人 ID
     */
    @Column(name = "REPORT_USER_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '举报人ID'")
    private String reportUserId;

    /**
     * 举报人耳语号
     */
    @Column(name = "REPORT_USER_ERYU_NO", columnDefinition = "VARCHAR(32) COMMENT '举报人ID'")
    private String reportUserEryuNo;

    /**
     * 被举报房间 ID
     */
    @Column(name = "REPORT_ROOM_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '被举报人房间ID'")
    private String chatRoomId;

    /**
     * 被举报房间名称
     */
    @Column(name = "REPORT_ROOM_NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '被举报房间名称'")
    private String chatRoomName;

    /**
     * 举报类型 ID
     */
    @Column(name = "TYPE_ID", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '举报类型ID'")
    private String typeId;

    /**
     * 举报类型名称
     */
    @Column(name = "TYPE_NAME", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '举报类型名称'")
    private String typeName;

    /**
     * 举报信息
     */
    @Column(name = "INFO", columnDefinition = "VARCHAR(256) NOT NULL COMMENT '举报信息'")
    private String info;

    /**
     * 状态
     */
    @Column(name = "STATE", columnDefinition = "INT(4) COMMENT '状态，-1为已删除，1为已处理，999为新增未处理'")
    private Integer state = NEW;

}