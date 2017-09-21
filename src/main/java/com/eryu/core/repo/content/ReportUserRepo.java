package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 聊天室举报类型数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface ReportUserRepo extends JpaRepository<ReportUser, String>, JpaSpecificationExecutor<ReportUser> {
}
