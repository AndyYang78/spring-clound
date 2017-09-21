package com.eryu.core.service.finance;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.finance.*;
import com.eryu.core.entity.dto.params.FinanceManualParam;
import com.eryu.core.entity.dto.params.FinanceParam;
import com.eryu.dto.SimpleResponse;

import java.util.List;

/**
 *
 * 查询财务相关信息
 * <p> </p>
 * Created by paopao on 2017/8/8.
 */
public interface FinanceService {

    /**
     * 打赏明细数据
     * @param financeParam 打赏数据查询
     * @return FinaceRewardDTO
     */
    PagingResultWrapper<FinaceRewardDTO> getFinaceReward(FinanceParam financeParam);

    /**
     * 账户明细数据
     * @param financeParam 打赏数据查询
     * @return FinaceRewardDTO
     */
    PagingResultWrapper<FinaceAccountDTO> getFinaceAccountBalance(FinanceParam financeParam);

    /**
     * 交易明细数据
     * @param financeParam 交易明细数据
     * @return FinaceRewardDTO
     */
    PagingResultWrapper<FinaceAccountDetailDTO> getTradeAccountDetail(FinanceParam financeParam);

    /**
     * 交易明细数据
     * @param financeParam 交易明细数据
     * @return FinaceRechargeListDTO
     */
    PagingResultWrapper<FinaceRechargeListDTO> getRechargeList(FinanceParam financeParam);

    /**
     * 获取提现的明细数据
     * @param financeParam 交易明细数据
     * @return FinaceRechargeListDTO
     */
    PagingResultWrapper<FinaceWithDrawDTO> getWithdrawList(FinanceParam financeParam);

    /**
     * 获取手工充值数据明细
     * @param financeParam 交易明细数据
     * @return FinaceRechargeListDTO
     */
    PagingResultWrapper<FinaceManualListDTO> getManualList(FinanceParam financeParam);

    /**
     * 手工给用户充值
     * @param param 手工给用户充值
     * @return SimpleResponse
     */
    SimpleResponse saveManualRecharge(FinanceManualParam param);




}
