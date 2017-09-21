package com.eryu.core.service.trade.impl;

import com.eryu.core.entity.po.trade.Reward;
import com.eryu.core.repo.trade.RewardRepo;
import com.eryu.core.service.trade.RewardRankGetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 打赏排行榜
 * Created by yangtao on 2017/7/18.
 */
@Service
public class RewardRankGetServiceImpl implements RewardRankGetService {

    @Resource
    private RewardRepo rewardRepo;

    /**
     * 列表
     */
    @Override
    public List<?> list() {
        List<Reward> list = rewardRepo.findAll();
        //
        return list;
    }
}