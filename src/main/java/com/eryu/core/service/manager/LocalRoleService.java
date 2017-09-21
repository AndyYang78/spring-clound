package com.eryu.core.service.manager;

import java.util.List;

/**
 * service
 * Created by yangtao on 2017/7/22.
 */
public interface LocalRoleService {

    /**
     * 初始化
     */
    void init();

    /**
     * 获取列表
     *
     * @return List<ExpressCompany>
     */
    List<?> list();

    /**
     * 添加
     *
     * @param name         名称
     * @param privilegeIds 权限的ID
     */
    void create(String name, List<Integer> privilegeIds);

    /**
     * 修改
     *
     * @param id           ID
     * @param privilegeIds 权限的ID
     */
    void update(Integer id, String name, List<Integer> privilegeIds);

    /**
     * 删除
     *
     * @param id ID
     */
    void remove(Integer id);
}