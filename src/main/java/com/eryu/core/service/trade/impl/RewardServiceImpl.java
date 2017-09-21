package com.eryu.core.service.trade.impl;

import com.eryu.core.entity.po.trade.Reward;
import com.eryu.core.repo.trade.RewardRepo;
import com.eryu.core.service.trade.RewardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 聊天室
 * Created by yangtao on 2017/7/18.
 */
@Service
public class RewardServiceImpl implements RewardService {

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