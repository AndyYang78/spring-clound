package com.eryu.controller.basic;

import com.eryu.core.service.basic.VersionService;
import com.eryu.dto.SimpleResponse;
import com.eryu.user.dto.request.CreateAndroidAPPVersionRequest;
import com.eryu.user.feignClients.APPVersionClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * APP版本相关API
 * Created by yangtao on 2017/8/11.
 */
@RestController
@RequestMapping("/content/app/android/version")
public class AndroidAPPVersionController {

    @Resource
    private APPVersionClient androidAPPVersionClient;

    @Resource
    private VersionService versionService;

    /**
     * 获取历史版本信息
     */
    @RequestMapping("/list")
    public SimpleResponse list() {
        return SimpleResponse.builder().data(versionService.androidAppVersionList()).build();
    }

    /**
     * 添加版本信息
     */
    @RequestMapping("/create")
    public SimpleResponse create(String name, Integer versionNumber, String downloadUrl, String description, String descriptionUrl, Boolean force) {
        CreateAndroidAPPVersionRequest request = new CreateAndroidAPPVersionRequest();
        request.setName(name);
        request.setDescription(description);
        request.setForceUpdate(force);
        request.setVersionNumber(versionNumber);
        request.setDescriptionUrl(descriptionUrl);
        request.setDownloadUrl(downloadUrl);
        return androidAPPVersionClient.createAndroid(request);
    }
}
