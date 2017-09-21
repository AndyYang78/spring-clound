package com.eryu.core.service.finance.impl;

import com.eryu.common.utils.TradeCode;
import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.finance.*;
import com.eryu.core.entity.dto.params.FinanceManualParam;
import com.eryu.core.entity.dto.params.FinanceParam;
import com.eryu.core.service.finance.FinanceService;
import com.eryu.dto.SimpleResponse;
import com.eryu.exception.BusinessException;
import com.eryu.mapper.FinanceMapper;
import com.eryu.trade.dto.request.FinanceManualRequest;
import com.eryu.trade.feignClients.WithdrawClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 查询财务相关信息
 *
 * Created by troubleMan on 2017/8/8.
 */
@Service("financeService")
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper financeMapper;

    @Resource
    private WithdrawClient withdrawClient;



    @Override
    public PagingResultWrapper<FinaceRewardDTO> getFinaceReward(FinanceParam financeParam) {

        if (StringUtils.hasText(financeParam.getDateStart()) && StringUtils.hasText(financeParam.getDateEnd())) {
            financeParam.setDateStart(String.format("%s %s", financeParam.getDateStart(), "00:00:00"));
            financeParam.setDateEnd(String.format("%s %s", financeParam.getDateEnd(), "23:59:59"));
        }
        List<FinaceRewardDTO> finaceRewardDTOList = financeMapper.queryRewardList(financeParam);

        int count = financeMapper.queryRewardCount(financeParam);

        PagingResultWrapper<FinaceRewardDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceRewardDTOList == null  ? new ArrayList<>() : finaceRewardDTOList);
        return wrapper;

    }

    @Override
    public PagingResultWrapper<FinaceAccountDTO> getFinaceAccountBalance(FinanceParam financeParam) {
        List<FinaceAccountDTO> finaceAccountDTOList = financeMapper.queryAccoutBalanceList(financeParam);

        int count = financeMapper.queryAccoutBalanceCount(financeParam);

        PagingResultWrapper<FinaceAccountDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceAccountDTOList == null  ? new ArrayList<>() : finaceAccountDTOList);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<FinaceAccountDetailDTO> getTradeAccountDetail(FinanceParam financeParam) {
        List<FinaceAccountDetailDTO> finaceAccountDTOList = financeMapper.queryTradeAccoutDetail(financeParam);

        int count = financeMapper.queryTradeAccoutCount(financeParam);
        PagingResultWrapper<FinaceAccountDetailDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceAccountDTOList == null  ? new ArrayList<>() : finaceAccountDTOList);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<FinaceRechargeListDTO> getRechargeList(FinanceParam financeParam) {
        //部分字段重置
        changeParams(financeParam);

        List<FinaceRechargeListDTO> finaceRechargeListDTOList = financeMapper.queryRechargeList(financeParam);

        int count = financeMapper.queryRechargeListCount(financeParam);
        PagingResultWrapper<FinaceRechargeListDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceRechargeListDTOList == null ? new ArrayList<>() : finaceRechargeListDTOList);
        return wrapper;
    }

    @Override
    public PagingResultWrapper<FinaceWithDrawDTO> getWithdrawList(FinanceParam financeParam) {
        if (StringUtils.hasText(financeParam.getDateStart()) && StringUtils.hasText(financeParam.getDateEnd())) {
            financeParam.setDateStart(String.format("%s %s", financeParam.getDateStart(), "00:00:00"));
            financeParam.setDateEnd(String.format("%s %s", financeParam.getDateEnd(), "23:59:59"));
        }
        List<FinaceWithDrawDTO> finaceWithDrawDTOList = financeMapper.queryWithdrawList(financeParam);

        int count = financeMapper.queryWithdrawListCount(financeParam);
        PagingResultWrapper<FinaceWithDrawDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceWithDrawDTOList == null ? new ArrayList<>() : finaceWithDrawDTOList);
        return wrapper;

    }

    @Override
    public PagingResultWrapper<FinaceManualListDTO> getManualList(FinanceParam financeParam) {
        //时间类型的重置
        if (StringUtils.hasText(financeParam.getDateStart()) && StringUtils.hasText(financeParam.getDateEnd())) {
            financeParam.setDateStart(String.format("%s %s", financeParam.getDateStart(), "00:00:00"));
            financeParam.setDateEnd(String.format("%s %s", financeParam.getDateEnd(), "23:59:59"));
        }
        List<FinaceManualListDTO> finaceManualListDTOS = financeMapper.queryManualList(financeParam);

        int count = financeMapper.queryManualCount(financeParam);
        PagingResultWrapper<FinaceManualListDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count / financeParam.getLimit());
        wrapper.setPageSize(financeParam.getLimit());
        wrapper.setData(finaceManualListDTOS == null ? new ArrayList<>() : finaceManualListDTOS);
        return wrapper;
    }

    @Override
    public SimpleResponse saveManualRecharge(FinanceManualParam param) {
        //查找用户的数据信息
        String userId = financeMapper.queryUserId(param.getMobile());
        if(StringUtils.isEmpty(userId)){
            throw new BusinessException(TradeCode.NO_USER);
        }
        param.setUserId(userId);
        FinanceManualRequest request =new FinanceManualRequest();
        BeanUtils.copyProperties(param, request);

        return withdrawClient.manualRecharge(request);
    }


    /**
     * 数据转换
     */
    private FinanceParam changeParams(FinanceParam financeParam) {
        //充值状态
        if (StringUtils.hasText(financeParam.getRechargeState())) {
            String[] rechargeState = financeParam.getRechargeState().split(",");
            financeParam.setRechargeStates(rechargeState);
        }
        //充值状态
        if (StringUtils.hasText(financeParam.getPayState())) {
            String[] payState = financeParam.getPayState().split(",");
            financeParam.setPayStates(payState);
        }
        //时间 充值
        if (StringUtils.hasText(financeParam.getRechargeStart()) && StringUtils.hasText(financeParam.getRechargeEnd())) {
            financeParam.setRechargeStart(String.format("%s %s", financeParam.getRechargeStart(), "00:00:00"));
            financeParam.setRechargeEnd(String.format("%s %s", financeParam.getRechargeEnd(), "23:59:59"));
        }
        //支付时间
        if (StringUtils.hasText(financeParam.getPayStart()) && StringUtils.hasText(financeParam.getPayEnd())) {
            financeParam.setPayStart(String.format("%s %s", financeParam.getRechargeStart(), "00:00:00"));
            financeParam.setPayEnd(String.format("%s %s", financeParam.getRechargeEnd(), "23:59:59"));
        }
        return financeParam;
    }
}
