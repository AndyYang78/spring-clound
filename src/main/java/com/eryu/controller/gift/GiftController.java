package com.eryu.controller.gift;

import com.eryu.core.entity.dto.common.PagingResultWrapper;

import com.eryu.core.entity.dto.gift.DynamicListDTO;
import com.eryu.core.entity.dto.gift.GiftListDTO;
import com.eryu.core.entity.dto.params.GiftParam;
import com.eryu.core.service.gift.GiftService;
import com.eryu.dto.SimpleResponse;
import com.eryu.trade.dto.request.GiftAddRequest;
import com.eryu.trade.feignClients.GiftClient;
import com.eryu.user.feignClients.DataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 *
 * 礼物 相关信息的查询
 *
 * Created by troubleMan on 2017/8/1.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @Resource
    private GiftClient giftClient;

    @Resource
    private DataClient dataClient;


    /**
     *
     * 获取礼物列表
     * @param giftParam 动态信息
     * @return SimpleResponse  返回礼物列表
     */
    @GetMapping(value = "/list")
    public PagingResultWrapper<GiftListDTO> queryGiftList(GiftParam giftParam) {
        return giftService.getGiftList(giftParam);
    }
    /**
     *
     * 添加礼物列表
     * @param giftParam 动态信息
     * @return SimpleResponse  返回礼物列表
     */
    @PostMapping(value = "/list/create")
    public SimpleResponse addGiftList(GiftAddRequest giftParam) {
        giftService.addGiftList(giftParam);

        return SimpleResponse.builder().build();
    }

    /**
     *
     * 修改礼物列表
     * @param giftParam 动态信息
     * @return SimpleResponse  返回礼物列表
     */
    @PostMapping(value = "/list/update")
    public SimpleResponse updateGiftList(GiftAddRequest giftParam) {
        giftService.updateGiftList(giftParam);
        //增加礼物配置 version +1
        dataClient.renewAPIVersion("chatroomGift");
        return SimpleResponse.builder().build();
    }

    /**
     *
     * 删除礼物列表
     * @param giftId 礼物id
     * @return SimpleResponse  消息是否成功
     */
    @PostMapping(value = "/list/delete")
    public SimpleResponse updateGiftList(String giftId) {
        return giftClient.deleteGifts(giftId);
    }

    /**
     *
     * 获取动效列表
     * @param giftParam 动态信息
     * @return SimpleResponse  获取动效列表
     */
    @GetMapping(value = "/dynamic/list")
    public PagingResultWrapper<DynamicListDTO> queryDynamicList(GiftParam giftParam) {
        return giftService.getDynamicList(giftParam);
    }
    /**
     *
     * 添加动效列表
     * @param giftParam
     * @return SimpleResponse  获取动效列表
     */
    @PostMapping(value = "/dynamic/add")
    public SimpleResponse createDynamicList(GiftParam giftParam) {
         giftService.addDynamicList(giftParam);
        return SimpleResponse.builder().build();
    }
    /**
     *
     * 修改动效列表
     * @param giftParam 传入参数
     * @return SimpleResponse  获取动效列表
     */
    @PostMapping(value = "/dynamic/update")
    public SimpleResponse updatetDynamicList(GiftParam giftParam) {
        giftService.updateDynamicList(giftParam);
        return SimpleResponse.builder().build();
    }

    /**
     *
     * 删除动效列表
     * @param dynamicId 传入参数
     * @return SimpleResponse  获取动效列表
     */
    @PostMapping(value = "/dynamic/delete")
    public SimpleResponse deleteDynamicList(String dynamicId) {
        giftService.deleteDynamicList(dynamicId);
        return SimpleResponse.builder().build();
    }

}
