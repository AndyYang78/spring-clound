package com.eryu.controller.basic;

import com.eryu.authentication.utils.QiniuAuthClient;
import com.eryu.content.client.BannerClient;
import com.eryu.content.params.ChatRoomBannerCreateParams;
import com.eryu.content.params.ChatRoomBannerUpdateParams;
import com.eryu.content.params.ChatRoomIdParams;
import com.eryu.core.service.basic.BannerService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 命令API
 */
@RestController
@RequestMapping("/content/banner")
public class BannerController {

    /**
     * Feign 客户端
     */
    @Resource
    private BannerClient bannerClient;

    @Resource
    private BannerService bannerService;

    @Resource
    private QiniuAuthClient qiniuAuthClient;

    /**
     * 可用banner列表
     */
    @RequestMapping("/list")
    public SimpleResponse list(String position, Boolean usable) {
        return SimpleResponse.builder().data(bannerService.list(position, usable)).build();
    }

    /**
     * 添加banner
     */
    @RequestMapping("/create")
    public SimpleResponse create(String name, String imgUrl, String targetUrl, String position, Integer weight) {
        ChatRoomBannerCreateParams params = new ChatRoomBannerCreateParams();
        params.setName(name);
        params.setImgUrl(imgUrl);
        params.setTargetUrl(targetUrl);
        params.setPosition(position);
        params.setWeight(weight);
        return bannerClient.create(params);
    }

    /**
     * 启用 banner
     */
    @RequestMapping("/open")
    public SimpleResponse open(String id) {
        ChatRoomIdParams params = new ChatRoomIdParams();
        params.setId(id);
        return bannerClient.open(params);
    }

    /**
     * 停用 banner
     */
    @RequestMapping("/close")
    public SimpleResponse close(String id) {
        ChatRoomIdParams params = new ChatRoomIdParams();
        params.setId(id);
        return bannerClient.close(params);
    }

    /**
     * 删除 banner
     */
    @RequestMapping("/remove")
    public SimpleResponse remove(String id) {
        ChatRoomIdParams params = new ChatRoomIdParams();
        params.setId(id);
        return bannerClient.remove(params);
    }

    /**
     * 更新 banner
     */
    @RequestMapping("/update")
    public SimpleResponse update(String id, String name, String imgUrl, String targetUrl, Integer weight) {
        ChatRoomBannerUpdateParams params = new ChatRoomBannerUpdateParams();
        params.setId(id);
        params.setName(name);
        params.setImgUrl(imgUrl);
        params.setTargetUrl(targetUrl);
        params.setWeight(weight);
        return bannerClient.update(params);
    }

    @RequestMapping("/upload/token/qiniu")
    public String getQiniuToken() {
        return qiniuAuthClient.uploadToken();
    }

}