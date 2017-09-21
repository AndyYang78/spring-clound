package com.eryu.controller.content;

import com.eryu.content.params.*;
import com.eryu.core.service.content.ChatRoomTypeService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 房间标签管理API
 */
@RestController
@RequestMapping(value = "/content/chatroom/type")
public class ChatRoomTypeController {

    @Resource
    private ChatRoomTypeService chatRoomTypeService;

    /**
     * 标签列表
     */
    @RequestMapping("/list")
    public SimpleResponse typeList() {
        return SimpleResponse.builder().data(chatRoomTypeService.typeList()).build();
    }

    /**
     * 创建标签
     */
    @RequestMapping("/create")
    public SimpleResponse createType(ChatRoomCreateTypeParams params) {
        chatRoomTypeService.createType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 启用标签
     */
    @RequestMapping("/open")
    public SimpleResponse openType(ChatRoomOpenTypeParams params) {
        chatRoomTypeService.openType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 通用标签
     */
    @RequestMapping("/close")
    public SimpleResponse closeType(ChatRoomCloseTypeParams params) {
        chatRoomTypeService.closeType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 修改标签
     */
    @RequestMapping("/update")
    public SimpleResponse updateType(ChatRoomUpdateTypeParams params) {
        chatRoomTypeService.updateType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 移除标签
     */
    @RequestMapping("/remove")
    public SimpleResponse removeType(ChatRoomRemoveTypeParams params) {
        chatRoomTypeService.removeType(params);
        return SimpleResponse.builder().build();
    }
}
