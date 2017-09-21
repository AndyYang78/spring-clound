package com.eryu.core.repo.trade;

import com.eryu.core.entity.po.trade.WithdrawPayBack;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 返回的打款数据 入库操作
 *
 * Created by troubleMan on 2017/6/28.
 */
public interface WithdrawPayRepo extends JpaRepository<WithdrawPayBack, String> {
}
