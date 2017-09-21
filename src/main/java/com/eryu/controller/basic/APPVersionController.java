package com.eryu.controller.basic;

import com.eryu.core.service.basic.VersionService;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.CreateAPPVersionRequest;
import com.eryu.user.dto.request.ModifyAPPVersionRequest;
import com.eryu.user.feignClients.APPVersionClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * APP版本相关API
 * Created by yangtao on 2017/8/11.
 */
@RestController
@RequestMapping("/content/app/version")
public class APPVersionController {

    @Resource
    private APPVersionClient appVersionClient;

    @Resource
    private VersionService versionService;

    /**
     * 获取历史版本信息
     */
    @RequestMapping("/list")
    public SimpleResponse list() {
        return SimpleResponse.builder().data(versionService.appVersionList()).build();
    }

    /**
     * 添加版本信息
     */
    @RequestMapping("/create")
    public SimpleResponse create(String name, String versionNumber, String os, String versionState, String description, Boolean force) {
        CreateAPPVersionRequest request = new CreateAPPVersionRequest();
        request.setName(name);
        request.setDescription(description);
        request.setForceUpdate(force);
        request.setVersionNumber(versionNumber);
        request.setOs(os);
        request.setVersionState(versionState);
        return appVersionClient.create(request);
    }

    /**
     * 更新app
     * 版本信息
     * @author troubleMan
     */
    @PostMapping("/update/app")
    public SimpleResponse updateAppVersion(ModifyAPPVersionRequest request) {
        return appVersionClient.updateAPPVersionForOperation(request);
    }
}
