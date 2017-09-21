package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ReportChatRoom;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 聊天室举报类型数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface ReportChatRoomRepo extends JpaRepository<ReportChatRoom, String>, JpaSpecificationExecutor<ReportChatRoom> {

    /**
     * 状态查找
     *
     * @param state 状态
     * @return 结果集
     */
    List<ReportChatRoom> findByStateNot(Integer state, Sort sort);
}
