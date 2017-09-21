package com.eryu.core.service.content;

import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.content.params.ChatRoomReportCreateParams;
import com.eryu.core.entity.dto.params.ReportListParams;

import java.util.List;

/**
 * 聊天室举报信息
 * Created by yangtao on 2017/7/18.
 */
public interface ChatRoomReportService {

    /**
     * 列表
     */
    List<?> list(ReportListParams params);

    /**
     * 创建
     */
    void create(ChatRoomReportCreateParams params);

    /**
     * 启用
     */
    void complete(ChatRoomIdParams params);

    /**
     * 删除
     */
    void remove(ChatRoomIdParams params);
}
