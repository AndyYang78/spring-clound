package com.eryu.core.entity.dto.finance;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * 财务打赏明细
 *
 * Created by troubleMan on 2017/8/8.
 */
@Getter
@Setter
public class FinaceRewardDTO {
    /**
     * 打赏用户Id
     */
    private String rewardId;
    /**
     * 打赏用户的昵称
     */
    private String rewarNick;
    /**
     * 受赏用户Id
     */
    private String awardId;
    /**
     * 受赏用户昵称
     */
    private String awardNick;
    /**
     * 打赏钻石数
     */
    private Integer diamonds;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 打赏前钻石数
     */
    private Integer beforeDiamond;
    /**
     * 打赏后钻石数
     */
    private Integer afterDiamond;

}
