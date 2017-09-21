package com.eryu.core.service.content.impl;

import com.eryu.content.client.ChatRoomClient;
import com.eryu.content.params.*;
import com.eryu.core.entity.po.content.ChatRoom;
import com.eryu.core.entity.po.content.ChatRoomType;
import com.eryu.core.repo.content.ChatRoomTypeRepo;
import com.eryu.core.service.content.ChatRoomTypeService;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
@Service
public class ChatRoomTypeServiceImpl implements ChatRoomTypeService {

    @Resource
    private ChatRoomTypeRepo chatRoomTypeRepo;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ChatRoomClient chatRoomClient;

    /**
     * 聊天室类型列表
     */
    @Override
    public List<?> typeList() {
//        Set<String> chatRoomTypes = stringRedisTemplate.opsForSet().members(REDIS_KEY_CHATROOM_TYPE);
//        ArrayList<ChatRoomType> chatRoomTypeList = new ArrayList<>(chatRoomTypes.size());
//        chatRoomTypes.forEach(object -> chatRoomTypeList.add(toEntity(object, ChatRoomType.class)));
//        return chatRoomTypeList;
        List<ChatRoomType> list = chatRoomTypeRepo.findAll(new Sort(Sort.Direction.DESC, "state"));
        return list;
    }

    /**
     * 创建类型
     */
    @Override
    public void createType(ChatRoomCreateTypeParams params) {
        handleResponse(chatRoomClient.createType(params));
    }

    /**
     * 启用类型
     */
    @Override
    public void openType(ChatRoomOpenTypeParams params) {
        handleResponse(chatRoomClient.openType(params));
    }

    /**
     * 停用类型
     */
    @Override
    public void closeType(ChatRoomCloseTypeParams params) {
        handleResponse(chatRoomClient.closeType(params));

    }

    /**
     * 更新类型
     */
    @Override
    public void updateType(ChatRoomUpdateTypeParams params) {
        handleResponse(chatRoomClient.updateType(params));

    }

    /**
     * 删除类型
     */
    @Override
    public void removeType(ChatRoomRemoveTypeParams params) {
        handleResponse(chatRoomClient.removeType(params));
    }

    private <T> T toEntity(String object, Class<T> valueType) {
        try {
            return objectMapper.readValue(object, valueType);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    private void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(4000, response.getMessage());
    }
}
