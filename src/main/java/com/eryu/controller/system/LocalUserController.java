package com.eryu.controller.system;

import com.eryu.common.helper.ObjectHelper;
import com.eryu.common.helper.ParamHelper;
import com.eryu.core.service.manager.LocalUserService;
import com.eryu.dto.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;


/**
 * 系统用户管理
 * Created by yangtao on 2017/7/22.
 */
@RestController
@RequestMapping("/content/system/user")
@Getter
public class LocalUserController implements ObjectHelper, ParamHelper {

    @Resource
    private LocalUserService service;

    @Resource
    private ObjectMapper objectMapper;

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
     * 添加
     *
     * @param name     名称
     * @param password 密码
     * @param roles    用户的角色
     * @return response
     */
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SYSTEM_SYSTEM-USER"})
    @PostMapping("/create")
    public SimpleResponse create(String name, String password, String roles) {
        List<Integer> roleIds = toList(roles, Integer.class);
        service.create(name, password, roleIds);
        return SimpleResponse.builder().build();
    }

    /**
     * 修改
     *
     * @param id       ID
     * @param name     名称
     * @param password 密码
     * @param roles    用户的角色
     * @return response
     */
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SYSTEM_SYSTEM-USER"})
    @PostMapping("/update")
    public SimpleResponse update(Integer id, String name, String password, String roles) {
        List<Integer> roleIds = toList(roles, Integer.class);
        service.update(id, name, password, roleIds);
        return SimpleResponse.builder().build();
    }

    /**
     * 删除
     *
     * @param id ID
     * @return ExpressCompany
     */
    @RolesAllowed({"ROLE_ADMIN", "ROLE_SYSTEM_SYSTEM-USER"})
    @PostMapping("/remove")
    public SimpleResponse remove(Integer id) {
        empty(id, "ID不能为空！");
        service.remove(id);
        return SimpleResponse.builder().build();
    }
}