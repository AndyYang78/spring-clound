package com.eryu.core.repo.content;

import com.eryu.core.entity.po.content.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Banner数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface BannerRepo extends JpaRepository<Banner, String>, JpaSpecificationExecutor<Banner> {
}
