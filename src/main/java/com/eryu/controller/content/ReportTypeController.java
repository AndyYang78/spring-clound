package com.eryu.controller.content;

import com.eryu.content.params.*;
import com.eryu.core.service.content.ChatRoomReportTypeService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 房间举报类型管理API
 */
@RestController
@RequestMapping(value = "/content/chatroom/report/type")
public class ReportTypeController {

    @Resource
    private ChatRoomReportTypeService chatRoomReportTypeService;

    /**
     * 标签列表
     */
    @PostMapping("/list")
    public SimpleResponse typeList() {
        return SimpleResponse.builder().data(chatRoomReportTypeService.typeList()).build();
    }

    /**
     * 创建标签
     */
    @RequestMapping("/create")
    public SimpleResponse createType(ChatRoomReportTypeCreateParams params) {
        chatRoomReportTypeService.createReportType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 启用标签
     */
    @RequestMapping("/open")
    public SimpleResponse openType(ChatRoomIdParams params) {
        chatRoomReportTypeService.openReportType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 关闭标签
     */
    @RequestMapping("/close")
    public SimpleResponse closeType(ChatRoomIdParams params) {
        chatRoomReportTypeService.closeReportType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 修改标签
     */
    @RequestMapping("/update")
    public SimpleResponse updateType(ChatRoomReportTypeUpdateParams params) {
        chatRoomReportTypeService.updateReportType(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 移除标签
     */
    @RequestMapping("/remove")
    public SimpleResponse removeType(ChatRoomIdParams params) {
        chatRoomReportTypeService.removeReportType(params);
        return SimpleResponse.builder().build();
    }
}
