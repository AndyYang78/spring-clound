package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 聊天室数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface ChatRoomRepo extends JpaRepository<ChatRoom, String> {
}
