package com.eryu.core.repo.user;

import com.eryu.core.entity.po.user.AndroidAppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * APP版本信息
 * Created by yangtao on 2017/6/28.
 */
public interface AndroidAPPVersionRepo extends JpaRepository<AndroidAppVersion, String> {
}
