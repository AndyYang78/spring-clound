package com.eryu.core.service.basic.impl;

import com.eryu.core.repo.user.APIVersionRepo;
import com.eryu.core.repo.user.APPSettingRepo;
import com.eryu.core.repo.user.APPVersionRepo;
import com.eryu.core.repo.user.AndroidAPPVersionRepo;
import com.eryu.core.service.basic.VersionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * service impl
 * Created by yangtao on 2017/8/1.
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Resource
    private APIVersionRepo apiVersionRepo;

    @Resource
    private APPVersionRepo appVersionRepo;

    @Resource
    private AndroidAPPVersionRepo androidAPPVersionRepo;

    @Resource
    private APPSettingRepo appSettingRepo;

    /**
     * 内容版本信息列表
     */
    @Override
    public List<?> list() {
        return apiVersionRepo.findAll();
    }

    /**
     * APP版本信息列表
     */
    @Override
    public List<?> appVersionList() {
        return appVersionRepo.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    /**
     * APP版本信息列表
     */
    @Override
    public List<?> androidAppVersionList() {
        return androidAPPVersionRepo.findAll(new Sort(Sort.Direction.DESC, "autoIndex"));
    }

    /**
     * 列表
     */
    @Override
    public List<?> getAppSetting() {
        return appSettingRepo.findAll();
    }
}
