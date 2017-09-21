package com.eryu.core.service.content.impl;

import com.eryu.content.client.ChatRoomClient;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomReportTypeCreateParams;
import com.eryu.content.params.ChatRoomReportTypeUpdateParams;
import com.eryu.core.repo.content.ReportTypeRepo;
import com.eryu.core.service.content.ChatRoomReportTypeService;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
@Service
public class ChatRoomReportTypeServiceImpl implements ChatRoomReportTypeService {

    @Resource
    private ReportTypeRepo reportTypeRepo;

    @Resource
    private ChatRoomClient chatRoomClient;

    /**
     * 聊天室举报类型列表
     */
    @Override
    public List<?> typeList() {
//        return chatRoomReportTypeRepo.findByStateNot(ReportType.DELETED, new Sort(Sort.Direction.DESC, "state"));
        return reportTypeRepo.findAll(new Sort(Sort.Direction.DESC, "state"));
    }

    /**
     * 创建类型
     */
    @Override
    public void createReportType(ChatRoomReportTypeCreateParams params) {
        handleResponse(chatRoomClient.createReportType(params));
    }

    /**
     * 启用类型
     */
    @Override
    public void openReportType(ChatRoomIdParams params) {
        handleResponse(chatRoomClient.openReportType(params));
    }

    /**
     * 停用类型
     */
    @Override
    public void closeReportType(ChatRoomIdParams params) {
        handleResponse(chatRoomClient.closeReportType(params));

    }

    /**
     * 更新类型
     */
    @Override
    public void updateReportType(ChatRoomReportTypeUpdateParams params) {
        handleResponse(chatRoomClient.updateReportType(params));
    }

    /**
     * 删除类型
     */
    @Override
    public void removeReportType(ChatRoomIdParams params) {
        handleResponse(chatRoomClient.removeReportType(params));
    }

    private void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(4000, response.getMessage());
    }
}
