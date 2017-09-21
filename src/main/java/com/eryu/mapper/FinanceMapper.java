package com.eryu.mapper;

import com.eryu.core.entity.dto.finance.*;
import com.eryu.core.entity.dto.params.FinanceParam;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 财务mapper接口
 * <p></p>
 *
 * Created by troubleMan on 2017/8/8.
 */
@Mapper
public interface FinanceMapper {

    /**
     * 查询财务的相关列表
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceRewardDTO
     */
    List<FinaceRewardDTO> queryRewardList(FinanceParam params);

    /**
     * 查询财务的相关列表--计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return int
     */
    int queryRewardCount(FinanceParam params);

    /**
     * 查询账户余额
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FamilyIncomDTO
     */
    List<FinaceAccountDTO> queryAccoutBalanceList(FinanceParam params);

    /**
     * 查询账户余额 --计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return int
     */
    int queryAccoutBalanceCount(FinanceParam params);

    /**
     * 查询账户交易明细
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceAccountDetailDTO
     */
    List<FinaceAccountDetailDTO> queryTradeAccoutDetail(FinanceParam params);

    /**
     * 查询账户交易明细 --计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return int
     */
    int queryTradeAccoutCount(FinanceParam params);

    /**
     * 查询充值订单明细
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceRechargeListDTO
     */
    List<FinaceRechargeListDTO> queryRechargeList(FinanceParam params);

    /**
     * 查询充值订单明细 --计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return int
     */
    int queryRechargeListCount(FinanceParam params);

    /**
     * 获取提现数据
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceWithDrawDTO
     */
    List<FinaceWithDrawDTO> queryWithdrawList(FinanceParam params);


    /**
     * 获取提现数据 --计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceWithDrawDTO
     */
    int queryWithdrawListCount(FinanceParam params);

    /**
     * 获取手工充值数据明细
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceWithDrawDTO
     */
    List<FinaceManualListDTO> queryManualList(FinanceParam params);

    /**
     * 获取手工充值数据-计数
     *
     * @param params 传入参数 分页数据， 时间，昵称等
     * @return FinaceWithDrawDTO
     */
    int queryManualCount(FinanceParam params);
    /**
     * 通过手机号码获取用户的Id
     *
     * @param mobile 手机号码
     * @return String
     */
    String queryUserId(@Param("mobile") String mobile);






}
