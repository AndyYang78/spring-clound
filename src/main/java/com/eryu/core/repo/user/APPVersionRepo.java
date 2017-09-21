package com.eryu.core.repo.user;

import com.eryu.core.entity.po.user.APPVersion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * APP版本信息
 * Created by yangtao on 2017/6/28.
 */
public interface APPVersionRepo extends JpaRepository<APPVersion, String> {
}
