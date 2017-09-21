package com.eryu.controller.system;

import com.eryu.common.helper.ParamHelper;
import com.eryu.core.service.manager.LocalPrivilegeService;
import com.eryu.dto.SimpleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;


/**
 * 权限管理
 * Created by yangtao on 2017/7/22.
 */
@RestController
@RequestMapping("/content/system/privilege")
public class LocalPrivilegeController implements ParamHelper {

    @Resource
    private LocalPrivilegeService service;

    /**
     * 列表
     *
     * @return List<>
     */
    @GetMapping("/list")
    public SimpleResponse list() {
        return SimpleResponse.builder().data(service.list()).build();
    }

    /**
     * 新增权限
     *
     * @param name     权限名称
     * @param model    权限类型
     * @param page     权限页面
     * @param point    权限点
     * @param parentId 父权限ID
     * @return SimpleResponse
     */
    @RequestMapping("/create")
    public SimpleResponse create(String name, String model, String page, String point, Integer parentId) {
        service.create(name, model, page, point, parentId);
        return SimpleResponse.builder().build();
    }

    /**
     * 删除
     *
     * @param id ID
     * @return ExpressCompany
     */
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SYSTEM_SYSTEM-PRIVILEGE"})
    @PostMapping("/remove")
    public SimpleResponse remove(Integer id) {
        empty(id, "ID不能为空！");
        service.remove(id);
        return SimpleResponse.builder().build();
    }
}