package com.eryu.mapper;

import com.eryu.core.entity.dto.gift.DynamicListDTO;
import com.eryu.core.entity.dto.gift.GiftListDTO;
import com.eryu.core.entity.dto.params.GiftParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询礼物的相关的信息列表
 *
 * Created by troubleMan on 2017/8/1.
 */
@Mapper
public interface GiftMapper {

    /**
     * 查询礼物的列表
     * @param giftParam 礼物名称，状态，是否启用
     * @return GiftListDTO
     */
    List<GiftListDTO> getQueryGift(GiftParam giftParam);

    /**
     * 查询礼物的列表 --数量
     * @param giftParam 礼物名称，状态，是否启用
     * @return GiftListDTO
     */
    int getQueryGiftCount(GiftParam giftParam);

    /**
     * 查询动效的列表
     * @param giftParam 动效名称，状态，
     * @return GiftListDTO
     */
    List<DynamicListDTO> getDynamicList(GiftParam giftParam);

    /**
     * 查询动效的列表 --数量
     * @param giftParam 动效名称，状态，
     * @return GiftListDTO
     */
    int getDynamicCount(GiftParam giftParam);
    /**
     * 增加动效
     * @param giftParam 动效名称，状态，地址
     */
    void addDynamicList(GiftParam giftParam);
    /**
     * 修改动效
     * @param giftParam 动效名称，状态，地址
     */
    void updateDynamicList(GiftParam giftParam);

    /**
     * 删除动效
     * @param dynamicId 动效Id
     */
    void deleteDynamicList(@Param("dynamicId") String dynamicId);
}
