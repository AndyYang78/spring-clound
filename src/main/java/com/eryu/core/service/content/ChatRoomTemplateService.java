package com.eryu.core.service.content;

import com.eryu.content.params.ChatRoomCreateTemplateParams;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomRemoveTypeParams;

import java.util.List;

/**
 * 聊天室模板
 * Created by yangtao on 2017/7/18.
 */
public interface ChatRoomTemplateService {

    /**
     * 聊天室模板列表
     */
    List<?> typeList();

    /**
     * 创建模板
     */
    void createTemplate(ChatRoomCreateTemplateParams params);

    /**
     * 启用模板
     */
    void openTemplate(ChatRoomIdParams params);

    /**
     * 停用模板
     */
    void closeTemplate(ChatRoomIdParams params);

    /**
     * 更新模板
     */
    void updateTemplate(ChatRoomRemoveTypeParams params);

    /**
     * 删除模板
     */
    void removeTemplate(ChatRoomIdParams params);
}
