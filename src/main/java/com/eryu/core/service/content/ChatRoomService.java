package com.eryu.core.service.content;

import com.eryu.core.entity.po.content.ChatRoom;

import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
public interface ChatRoomService {

    //聊天室
    String REDIS_KEY_CHATROOM = "MAP_CHAT_ROOM";
    String REDIS_KEY_CHATROOM_CLOSED = "MAP_CHAT_ROOM_CLOSED";

    /**
     * 获取在线聊天室列表
     *
     * @param userId   用户ID
     * @param userName 用户昵称
     * @param roomName 房间名称
     * @param typeId   类型
     * @return 结果集
     */
    List<ChatRoom> listOnline(String userId, String userName, String roomName, String typeId);

    /**
     * 获取聊天室列表
     *
     * @param creator   用户ID
     * @param creatorName 用户昵称
     * @param roomName 房间名称
     * @param typeId   类型
     * @return 结果集
     */
    List<?> list(String creator, String creatorName, String roomName, String typeId);
}
