package com.eryu.core.repo.user;

import com.eryu.core.entity.po.user.FeedbackAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据持久层
 * Created by yangtao on 2017/6/28.
 */
public interface FeedbackAttachmentRepo extends JpaRepository<FeedbackAttachment, String> {
}
