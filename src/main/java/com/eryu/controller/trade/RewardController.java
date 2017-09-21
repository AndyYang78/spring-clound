package com.eryu.controller.trade;

import com.eryu.core.service.trade.RewardService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 打赏API
 */
@RestController
@RequestMapping(value = "/trade/reward")
public class RewardController {

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
