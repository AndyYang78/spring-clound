package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ReportType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 聊天室举报类型数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface ReportTypeRepo extends JpaRepository<ReportType, String> {

    /**
     * 状态查找
     *
     * @param state 状态
     * @return 结果集
     */
    List<ReportType> findByStateNot(Integer state, Sort sort);
}
