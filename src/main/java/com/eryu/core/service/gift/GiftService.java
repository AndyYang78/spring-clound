package com.eryu.core.service.gift;

import com.eryu.core.entity.dto.common.PagingResultWrapper;
import com.eryu.core.entity.dto.gift.DynamicListDTO;
import com.eryu.core.entity.dto.gift.GiftListDTO;
import com.eryu.core.entity.dto.params.GiftParam;


/**
 *
 * 查询礼物的相关信息
 *
 * Created by troubleMan on 2017/8/1.
 */
public interface GiftService {

    /**
     * 查询礼物列表
     *
     * @return GiftListDTO
     */
    PagingResultWrapper<GiftListDTO> getGiftList(GiftParam giftParam);

    /**
     * 增加礼物列表
     *
     * @return GiftListDTO
     */
    void addGiftList(com.eryu.trade.dto.request.GiftAddRequest giftParam);

    /**
     * 更新礼物列表
     *
     * @return GiftListDTO
     */
    void updateGiftList(com.eryu.trade.dto.request.GiftAddRequest giftParam);

    /**
     * 查询动效列表
     *@giftParam 动效的名称，状态
     * @return DynamicListDTO
     */
    PagingResultWrapper<DynamicListDTO> getDynamicList(GiftParam giftParam);
    /**
     * 增加动效列表
     *@giftParam 动效的名称，状态等信息新增
     * @return null
     */
    void addDynamicList(GiftParam giftParam);

    /**
     * 修改动效列表
     *@giftParam 动效的名称，状态等信息修改
     * @return null
     */
    void updateDynamicList(GiftParam giftParam);
    /**
     * 删除动效列表
     *@giftParam 删除动效列表
     * @return null
     */
    void deleteDynamicList(String dynamicId);
}
