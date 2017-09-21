package com.eryu.core.service.gift.impl;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.gift.DynamicListDTO;
import com.eryu.core.entity.dto.gift.GiftListDTO;
import com.eryu.core.entity.dto.params.GiftAddRequest;
import com.eryu.core.entity.dto.params.GiftParam;
import com.eryu.core.service.gift.GiftService;
import com.eryu.mapper.GiftMapper;
import com.eryu.trade.feignClients.GiftClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * 礼物相关的实现的方法
 *
 * Created by troubleMan on 2017/8/1.
 */
@Service("giftService")
public class GiftServiceImpl implements GiftService {

    @Resource
    private GiftMapper giftMapper;

    @Resource
    private GiftClient giftClient;



    @Override
    public PagingResultWrapper<GiftListDTO> getGiftList(GiftParam giftParam) {
        //查询礼物的列表
        List<GiftListDTO> giftListDTOList = giftMapper.getQueryGift(giftParam);
        int count = giftMapper.getQueryGiftCount(giftParam);
        PagingResultWrapper<GiftListDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count/giftParam.getLimit());
        wrapper.setPageSize(giftParam.getLimit());
        wrapper.setData((giftListDTOList == null || giftListDTOList.isEmpty()) ? new ArrayList<>() : giftListDTOList);
        return wrapper;
    }

    @Override
    public void addGiftList(com.eryu.trade.dto.request.GiftAddRequest giftParam) {
        giftClient.createGifts(giftParam);
    }

    @Override
    public void updateGiftList(com.eryu.trade.dto.request.GiftAddRequest giftParam) {
        giftClient.updateGifts(giftParam);
    }

    @Override
    public PagingResultWrapper<DynamicListDTO> getDynamicList(GiftParam giftParam) {
        //查询动效的列表
        List<DynamicListDTO> giftListDTOList = giftMapper.getDynamicList(giftParam);
        int count = giftMapper.getDynamicCount(giftParam);
        PagingResultWrapper<DynamicListDTO> wrapper = new PagingResultWrapper<>();
        wrapper.setTotal(count);
        wrapper.setTotalPage(count/giftParam.getLimit());
        wrapper.setPageSize(giftParam.getLimit());
        wrapper.setData((giftListDTOList == null || giftListDTOList.isEmpty()) ? new ArrayList<>() : giftListDTOList);
        return wrapper;
    }

    @Override
    public void addDynamicList(GiftParam giftParam) {
        String uuid = UUID.randomUUID().toString().replace("-", ""); //获取UUID并转化为String对象
        giftParam.setDynamicId(uuid);
        giftMapper.addDynamicList(giftParam);
    }

    @Override
    public void updateDynamicList(GiftParam giftParam) {
        giftMapper.updateDynamicList(giftParam);
    }

    @Override
    public void deleteDynamicList(String dynamicId) {
        giftMapper.deleteDynamicList(dynamicId);
    }
}
