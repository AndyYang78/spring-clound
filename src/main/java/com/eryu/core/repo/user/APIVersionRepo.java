package com.eryu.core.repo.user;

import com.eryu.core.entity.po.user.APIVersion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据持久层
 * Created by yangtao on 2017/6/28.
 */
public interface APIVersionRepo extends JpaRepository<APIVersion, String> {
}
