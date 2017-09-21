package com.eryu.core.service.content.impl;

import com.eryu.content.client.ChatRoomTemplateClient;
import com.eryu.content.params.ChatRoomCreateTemplateParams;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomRemoveTypeParams;
import com.eryu.core.repo.content.ChatRoomTemplateRepo;
import com.eryu.core.service.content.ChatRoomTemplateService;
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
public class ChatRoomTemplateServiceImpl implements ChatRoomTemplateService {

    @Resource
    private ChatRoomTemplateRepo chatRoomTemplateRepo;

    @Resource
    private ChatRoomTemplateClient chatRoomTemplateClient;

    /**
     * 聊天室模板列表
     */
    @Override
    public List<?> typeList() {
        return chatRoomTemplateRepo.findAll(new Sort(Sort.Direction.DESC, "state"));
    }

    /**
     * 创建模板
     */
    @Override
    public void createTemplate(ChatRoomCreateTemplateParams params) {
        handleResponse(chatRoomTemplateClient.createTemplate(params));
    }

    /**
     * 启用模板
     */
    @Override
    public void openTemplate(ChatRoomIdParams params) {
        handleResponse(chatRoomTemplateClient.openTemplate(params));
    }

    /**
     * 停用模板
     */
    @Override
    public void closeTemplate(ChatRoomIdParams params) {
        handleResponse(chatRoomTemplateClient.closeTemplate(params));

    }

    /**
     * 更新模板
     */
    @Override
    public void updateTemplate(ChatRoomRemoveTypeParams params) {
        handleResponse(chatRoomTemplateClient.updateTemplate(params));

    }

    /**
     * 删除模板
     */
    @Override
    public void removeTemplate(ChatRoomIdParams params) {
        handleResponse(chatRoomTemplateClient.removeTemplate(params));
    }

    private void handleResponse(SimpleResponse response) {
        if (!response.getSuccess())
            throw new BusinessException(4000, response.getMessage());
    }
}
