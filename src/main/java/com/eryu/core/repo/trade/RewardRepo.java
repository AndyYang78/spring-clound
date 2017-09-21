package com.eryu.core.repo.trade;

import com.eryu.core.entity.po.trade.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据持久化
 * Created by yangtao on 2017/6/28.
 */
public interface RewardRepo extends JpaRepository<Reward, String> {
}
