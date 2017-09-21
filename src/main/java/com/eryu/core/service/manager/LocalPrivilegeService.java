package com.eryu.core.service.manager;


import com.eryu.core.entity.po.manager.LocalPrivilege;

import java.util.List;

/**
 * service
 * Created by yangtao on 2017/7/22.
 */
public interface LocalPrivilegeService {

    /**
     * 权限初始化
     */
    void init();

    /**
     * 获取列表
     *
     * @return List<LocalPrivilege>
     */
    List<LocalPrivilege> list();

    /**
     * 获取列表
     *
     * @return List<LocalPrivilege>
     */
    List<LocalPrivilege> listAll();

    /**
     * 新增权限
     *
     * @param name     权限名称
     * @param model    权限类型
     * @param page     权限页面
     * @param point    权限点
     * @param parentId 父权限ID
     */
    void create(String name, String model, String page, String point, Integer parentId);

    /**
     * 删除
     *
     * @param id ID
     */
    void remove(Integer id);
}