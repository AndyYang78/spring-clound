package com.eryu.core.service.manager;



import com.eryu.core.entity.po.manager.LocalUser;

import java.util.List;

/**
 * ...
 * Created by yangtao on 2017/7/17.
 */
public interface LocalUserService {

    /**
     * 初始化
     */
    void init();

    /**
     * 用户名查找
     *
     * @param userName 用户名
     * @return 用户信息
     */
    LocalUser findUserByName(String userName);

    /**
     * 获取列表
     *
     * @return List<ExpressCompany>
     */
    List<?> list();

    /**
     * 添加
     *
     * @param name     名称
     * @param password 密码
     * @param roles    用户的角色
     */
    void create(String name, String password, List<Integer> roles);

    /**
     * 修改
     *
     * @param id       ID
     * @param name     名称
     * @param password 密码
     * @param roleIds  用户的角色
     */
    void update(Integer id, String name, String password, List<Integer> roleIds);

    /**
     * 删除
     *
     * @param id ID
     */
    void remove(Integer id);
}
