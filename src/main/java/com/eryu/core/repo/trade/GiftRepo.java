package com.eryu.core.repo.trade;

import com.eryu.core.entity.po.trade.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 礼物数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface GiftRepo extends JpaRepository<Gift, String> {
}
