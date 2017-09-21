package com.eryu.controller.trade;

import com.eryu.core.service.trade.RewardService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 打赏排行榜API
 */
@RestController
@RequestMapping(value = "/trade/reward/rank/give")
public class RewardRankGiveController {

    @Resource
    private RewardService rewardService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public SimpleResponse typeList() {
        return SimpleResponse.builder().data(rewardService.list()).build();
    }
}
