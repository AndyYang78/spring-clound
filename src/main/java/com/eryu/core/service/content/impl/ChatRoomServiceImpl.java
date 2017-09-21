package com.eryu.core.service.content.impl;

import com.eryu.common.helper.ObjectHelper;
import com.eryu.content.client.ChatRoomClient;
import com.eryu.content.client.MessageClient;
import com.eryu.core.entity.po.content.ChatRoom;
import com.eryu.core.repo.content.ChatRoomRepo;
import com.eryu.core.repo.content.ChatRoomTypeRepo;
import com.eryu.core.service.content.ChatRoomService;
import com.eryu.dto.ListResponse;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import com.eryu.user.dto.response.UserResponse;
import com.eryu.user.feignClients.UserClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
@Service
@Getter
public class ChatRoomServiceImpl implements ChatRoomService, ObjectHelper {

    @Resource
    private ChatRoomTypeRepo chatRoomTypeRepo;

    @Resource
    private ChatRoomRepo chatRoomRepo;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ChatRoomClient chatRoomClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserClient userClient;

    @Resource
    private MessageClient messageClient;

    /**
     * 获取在线聊天室列表
     *
     * @param userId   用户ID
     * @param userName 用户昵称
     * @param roomName 房间名称
     * @param typeId   类型
     * @return 结果集
     */
    @Override
    public List<ChatRoom> listOnline(String userId, String userName, String roomName, String typeId) {
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(REDIS_KEY_CHATROOM);
        //缓存
        List<String> chatRoomList = ops.values();
        //转换
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomList.size());
        chatRoomList.forEach(object -> chatRooms.add(toEntity(object, ChatRoom.class)));

        //处理
        completeRoom(chatRooms, userId, userName, roomName, typeId);

        return chatRooms;
    }

    /**
     * 获取聊天室列表
     */
    @Override
    public List<?> list(String userId, String userName, String roomName, String typeId) {
        //获取全部聊天室列表
        BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(REDIS_KEY_CHATROOM);
        List<String> chatRoomList = ops.values();
        BoundHashOperations<String, String, String> opsClosed = stringRedisTemplate.boundHashOps(REDIS_KEY_CHATROOM_CLOSED);
        List<String> chatRoomListClosed = opsClosed.values();

        //转换
        List<ChatRoom> chatRooms = new ArrayList<>(chatRoomList.size());
        chatRoomList.forEach(object -> chatRooms.add(toEntity(object, ChatRoom.class)));
        chatRoomListClosed.forEach(object -> chatRooms.add(toEntity(object, ChatRoom.class)));

        //处理
        completeRoom(chatRooms, userId, userName, roomName, typeId);

        //置顶判断
        SimpleResponse<List<String>> response = chatRoomClient.topList();
        handleResponse(response);
        List<String> tops = response.getData();
        //用于存放置顶的房间
        List<ChatRoom> result = new ArrayList<>();
        Iterator<ChatRoom> iterator = chatRooms.iterator();
        while (iterator.hasNext()) {
            ChatRoom room = iterator.next();
            if (tops.contains(room.getId())) {
                room.setTop(true);
                result.add(room);
                iterator.remove();
            }
        }
        result.addAll(chatRooms);
        return result;
    }

    /**
     * 完善房间信息
     *
     * @param chatRooms 聊天室列表
     * @param userId    用户ID
     * @param userName  用户昵称
     * @param roomName  房间名称
     * @param typeId    类型
     */
    private void completeRoom(List<ChatRoom> chatRooms, String userId, String userName, String roomName, String typeId) {
        List<String> userIds = new ArrayList<>(chatRooms.size());
        chatRooms.forEach(chatRoom -> userIds.add(chatRoom.getCreator()));
        //用户列表
        ListResponse<UserResponse> listResponse = userClient.getUsers(userIds);
        if (null == listResponse)
            throw new BusinessException(3000, "获取用户信息失败！");
        Iterator<ChatRoom> iterable = chatRooms.iterator();
        while (iterable.hasNext()) {
            ChatRoom room = iterable.next();
            for (UserResponse user : listResponse.getData()) {
                if (room.getCreator().equals(user.getId())) {
                    //用户ID筛选
                    if (StringUtils.hasText(userId)) {
                        if (user.getId() == null || !user.getId().contains(userId)) {
                            iterable.remove();
                            break;
                        }
                    }
                    //用户昵称筛选
                    if (StringUtils.hasText(userName)) {
                        if (user.getUsername() == null || !user.getUsername().contains(userName)) {
                            iterable.remove();
                            break;
                        }
                    }
                    //房间名称
                    if (StringUtils.hasText(roomName)) {
                        if (room.getName() == null || !room.getName().contains(roomName)) {
                            iterable.remove();
                            break;
                        }
                    }
                    //房间模板
                    if (StringUtils.hasText(typeId)) {
                        if (!room.getType().getId().equals(typeId)) {
                            iterable.remove();
                            break;
                        }
                    }
                    room.setLogo(user.getAvatar());
                    room.setCreatorName(user.getUsername());
                }
            }
        }
    }

    private void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(response.getCode(), response.getMessage());
    }
}
