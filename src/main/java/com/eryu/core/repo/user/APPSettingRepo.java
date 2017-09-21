package com.eryu.core.repo.user;

import com.eryu.core.entity.po.user.APPSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据持久层 t_app_setting
 * Created by yangtao on 2017/6/28.
 */
public interface APPSettingRepo extends JpaRepository<APPSetting, String> {
}
