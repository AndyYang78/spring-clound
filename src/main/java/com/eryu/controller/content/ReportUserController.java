package com.eryu.controller.content;

import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.UserReportCreateParams;
import com.eryu.core.entity.dto.params.ReportListParams;
import com.eryu.core.service.content.UserReportService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 举报信息管理API
 */
@RestController
@RequestMapping(value = "/content/user/report")
public class ReportUserController {

    @Resource
    private UserReportService chatRoomReportService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public SimpleResponse list(@RequestBody ReportListParams params) {
        return SimpleResponse.builder().data(chatRoomReportService.list(params)).build();
    }

    /**
     * 创建
     */
    @RequestMapping("/create")
    public SimpleResponse create(UserReportCreateParams params) {
        chatRoomReportService.create(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 完成
     */
    @RequestMapping("/complete")
    public SimpleResponse complete(ChatRoomIdParams params) {
        chatRoomReportService.complete(params);
        return SimpleResponse.builder().build();
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public SimpleResponse remove(ChatRoomIdParams params) {
        chatRoomReportService.remove(params);
        return SimpleResponse.builder().build();
    }
}
