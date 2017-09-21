package com.eryu.controller.basic;

import com.eryu.core.service.basic.VersionService;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.ModifyAppSettingRequest;
import com.eryu.user.dto.request.ModifyVersionRequest;
import com.eryu.user.feignClients.DataClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 版本控制接口
 * Created by yangtao on 2017/8/1.
 */
@RestController
@RequestMapping(value = "/content/version")
public class VersionController {

    @Resource
    private VersionService versionService;

    @Resource
    private DataClient dataClient;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public SimpleResponse list() {
        return SimpleResponse.builder().data(versionService.list()).build();
    }

    /**
     * 更新API版本
     *
     * @param id         ID
     * @param apiVersion 版本
     * @param name       名称
     */
    @RequestMapping("/update")
    public com.eryu.dto.SimpleResponse update(String id, Integer apiVersion, String name) {
        ModifyVersionRequest request = new ModifyVersionRequest();
        request.setId(id);
        request.setApiVersion(apiVersion);
        request.setName(name);
        return dataClient.updateAPIVersion(request);
    }

    /**
     * 获取APP自动更新版本信息
     * @return SimpleResponse
     */
    @GetMapping("/app/list")
    public SimpleResponse appVersionList() {
        return SimpleResponse.builder().data(versionService.appVersionList()).build();
    }

    /**
     * 获取t_app_setting 的数据列表
     * @return SimpleResponse
     */
    @GetMapping("/setting/list")
    public SimpleResponse getAppSetting() {
        return SimpleResponse.builder().data(versionService.getAppSetting()).build();
    }

    /**
     * 更新兑换配置值
     * @return SimpleResponse
     */
    @PostMapping("/setting/update/app")
    public SimpleResponse updateAppSetting(ModifyAppSettingRequest request) {
        return dataClient.updateAppSetting(request);
    }
}
