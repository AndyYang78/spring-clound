package com.eryu.core.service.content;

import com.eryu.content.params.*;

import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
public interface ChatRoomReportTypeService {

    /**
     * 聊天室类型列表
     */
    List<?> typeList();

    /**
     * 创建类型
     */
    void createReportType(ChatRoomReportTypeCreateParams params);

    /**
     * 启用类型
     */
    void openReportType(ChatRoomIdParams params);

    /**
     * 停用类型
     */
    void closeReportType(ChatRoomIdParams params);

    /**
     * 更新类型
     */
    void updateReportType(ChatRoomReportTypeUpdateParams params);

    /**
     * 删除类型
     */
    void removeReportType(ChatRoomIdParams params);
}
