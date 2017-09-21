package com.eryu.core.entity.po.content;

import com.eryu.core.entity.po.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 聊天室实体
 * <p>
 * Created by yangtao on 2017/6/28.
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "T_CHATROOM")
public class ChatRoom extends AbstractEntity {

    //主持人（主持位）
    @Transient
    private String host;
    @Transient
    private String logo;
    @Transient
    private boolean top = false;
    @Transient
    private String creatorName;
    //流ID
    @Transient
    private String streamId;
    //房间类型
    @Transient
    private ChatRoomType type = new ChatRoomType();
    //聊天室成员列表
    @Transient
    private Set<String> members = new HashSet<>();
    //管理员列表
    @Transient
    private Set<String> managers = new HashSet<>();
    //黑名单用户列表
    @Transient
    private Set<String> blackUsers = new HashSet<>();
    //禁言用户列表
    @Transient
    private Set<String> silentUsers = new HashSet<>();
    //上座申请列表
    @Transient
    private Set<String> applies = new HashSet<>();
    //静音的座位
    @Transient
    private Set<Integer> silentSeats = new HashSet<>();
    //锁定的座位
    @Transient
    private Set<Integer> lockedSeats = new HashSet<>();
    //座位人员信息
    @Transient
    private Map<Integer, String> seats = new HashMap<>(11);

    /**
     * 创建者
     */
    @Column(name = "CREATE_BY", columnDefinition = "VARCHAR(32) NOT NULL COMMENT '创建者ID'")
    private String creator;
    /**
     * 聊天室ID（云信生成，用于与云信交互）
     */
    @Column(name = "NETEASE_ROOM_NUMBER", columnDefinition = "INT(16) COMMENT '聊天室ID（云信生成，用于与云信交互）'")
    private Long neteaseRoomNumber;
    /**
     * 聊天室ID（耳语本地生成，用于业务）
     */
    @Column(name = "LOCAL_ROOM_NUMBER", columnDefinition = "INT(16) NOT NULL COMMENT '聊天室ID（耳语本地生成，用于业务）'")
    private Long localRoomNumber;
    /**
     * zego 房间ID
     */
    @Column(name = "ZEGO_ROOM_ID", columnDefinition = "VARCHAR(32) COMMENT 'zego的房间ID'")
    private String zegoRoomId;
    /**
     * 聊天室名称
     */
    @Column(name = "NAME", columnDefinition = "VARCHAR(64) COMMENT '聊天室名称'")
    private String name;
    /**
     * 聊天室状态（-1=创建中...，1=正常）
     */
    @Column(name = "STATE", columnDefinition = "INT(4) COMMENT '聊天室状态（-1=创建中...，1=正常）'")
    private Integer state;
    /**
     * 聊天室打开状态
     */
    @Column(name = "VALID", columnDefinition = "INT(2) COMMENT '聊天室状态 true为打开，false为关闭'")
    private Boolean valid;
    /**
     * 公告
     */
    @Column(name = "ANNOUNCEMENT", columnDefinition = "VARCHAR(1024) COMMENT '聊天室公告'")
    private String announcement;
    /**
     * 直播地址
     */
    @Column(name = "BROADCAST_URL", columnDefinition = "VARCHAR(128) COMMENT '直播地址'")
    private String broadcastUrl;
    /**
     * 聊天室类型
     */
//    @JsonIgnore
    @Column(name = "ROOM_TYPE_ID", columnDefinition = "VARCHAR(32) COMMENT '聊天室类型'")
    private String typeId;
    /**
     * 在线人数
     */
    @Column(name = "ONLINE_USER_COUNT", columnDefinition = "INT(8) COMMENT '在线人数'")
    private Integer onlineUserCount;
    /**
     * 聊天室额外信息
     */
    @Column(name = "EXT", columnDefinition = "VARCHAR(1024) COMMENT '聊天室额外信息'")
    private String ext;
    /**
     * 房间流ID
     */
    @Column(name = "ROOM_STREAM_ID", columnDefinition = "VARCHAR(64) COMMENT '房间流ID'")
    private String roomStreamId;
    /**
     * 房间最大人数
     */
    @Column(name = "MAX_MEMBER", columnDefinition = "INT(8) COMMENT '房间最大人数'")
    private Integer maxMember;
}
