package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ChatRoomTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 聊天室模板数据持久化
 * Created by yangtao on 2017/8/24.
 */
public interface ChatRoomTemplateRepo extends JpaRepository<ChatRoomTemplate, String> {

    /**
     * 状态查找
     *
     * @param state 状态
     * @return 结果集
     */
    List<ChatRoomTemplate> findByState(Integer state);
}
