package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ChatRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 聊天室类型数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface ChatRoomTypeRepo extends JpaRepository<ChatRoomType, String> {

    /**
     * 名称查找
     *
     * @param name 类型名称
     * @return 结果列表
     */
    List<ChatRoomType> findByName(String name);
}
