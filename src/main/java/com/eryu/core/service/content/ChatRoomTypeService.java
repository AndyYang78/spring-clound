package com.eryu.core.service.content;

import com.eryu.content.params.*;

import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
public interface ChatRoomTypeService {

    //聊天室
    String REDIS_KEY_CHATROOM = "MAP_CHAT_ROOM";
    //聊天室类型
    String REDIS_KEY_CHATROOM_TYPE = "SET_CHAT_ROOM_TYPE";

    /**
     * 聊天室类型列表
     */
    List<?> typeList();

    /**
     * 创建类型
     */
    void createType(ChatRoomCreateTypeParams params);

    /**
     * 启用类型
     */
    void openType(ChatRoomOpenTypeParams params);

    /**
     * 停用类型
     */
    void closeType(ChatRoomCloseTypeParams params);

    /**
     * 更新类型
     */
    void updateType(ChatRoomUpdateTypeParams params);

    /**
     * 删除类型
     */
    void removeType(ChatRoomRemoveTypeParams params);
}
