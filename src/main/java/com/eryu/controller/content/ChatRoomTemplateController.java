package com.eryu.controller.content;

import com.eryu.content.params.ChatRoomCreateTemplateParams;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomRemoveTypeParams;
import com.eryu.core.service.content.ChatRoomTemplateService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 房间模板管理API
 */
@RestController
@RequestMapping(value = "/content/chatroom/template")
public class ChatRoomTemplateController {

    @Resource
    private ChatRoomTemplateService chatRoomTemplateService;

    /**
     * 标签列表
     */
    @RequestMapping("/list")
    public SimpleResponse typeList() {
        return SimpleResponse.builder().data(chatRoomTemplateService.typeList()).build();
    }

    /**
     * 创建标签
     */
    @RequestMapping("/create")
    public SimpleResponse createType(ChatRoomCreateTemplateParams params) {
        chatRoomTemplateService.createTemplate(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 启用标签
     */
    @RequestMapping("/open")
    public SimpleResponse openType(ChatRoomIdParams params) {
        chatRoomTemplateService.openTemplate(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 通用标签
     */
    @RequestMapping("/close")
    public SimpleResponse closeType(ChatRoomIdParams params) {
        chatRoomTemplateService.closeTemplate(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 修改标签
     */
    @RequestMapping("/update")
    public SimpleResponse updateType(ChatRoomRemoveTypeParams params) {
        chatRoomTemplateService.updateTemplate(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 移除标签
     */
    @RequestMapping("/remove")
    public SimpleResponse removeType(ChatRoomIdParams params) {
        chatRoomTemplateService.removeTemplate(params);
        return SimpleResponse.builder().build();
    }
}
