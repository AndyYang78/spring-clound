package com.eryu.core.service.manager.impl;

import com.eryu.common.helper.MapHelper;
import com.eryu.core.entity.po.manager.LocalRole;
import com.eryu.core.entity.po.manager.LocalUser;
import com.eryu.core.repo.manager.LocalRoleRepo;
import com.eryu.core.repo.manager.LocalUserRepo;
import com.eryu.core.service.manager.LocalUserService;
import com.eryu.exception.BusinessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * ...
 * Created by yangtao on 2017/7/17.
 */
@Service
public class LocalUserServiceImpl implements LocalUserService, MapHelper {

    @Resource
    private LocalUserRepo localUserRepo;

    @Resource
    private LocalRoleRepo localRoleRepo;

    /**
     * 初始化
     */
    @Override
    public void init() {
        if (localUserRepo.count() > 0)
            return;
        LocalUser user = new LocalUser();
        user.setName("eryu");
        user.setPassword(new Md5PasswordEncoder().encodePassword("123456", ""));
        localUserRepo.save(user);
        Set<LocalRole> roles = new HashSet<>();
        roles.addAll(localRoleRepo.findByName("超级管理员"));
        user.setRoles(roles);
        localUserRepo.save(user);
    }

    @Override
    public LocalUser findUserByName(String userName) {
        return localUserRepo.findUserByName(userName);
    }

    /**
     * 获取列表
     *
     * @return List<ExpressCompany>
     */
    @Override
    public List<?> list() {
        return localUserRepo.findAll();
    }

    /**
     * 添加
     *
     * @param name     名称
     * @param password 密码
     * @param roleIds  用户的角色
     */
    @Override
    public void create(String name, String password, List<Integer> roleIds) {
        LocalUser user = new LocalUser();
        user.setName(name);
        user.setPassword(new Md5PasswordEncoder().encodePassword(password, ""));
        localUserRepo.save(user);
        Set<LocalRole> roles = new HashSet<>(capacity(roleIds.size()));
        roles.addAll(localRoleRepo.findAll(roleIds));
        user.setRoles(roles);
        localUserRepo.save(user);
    }

    /**
     * 修改
     *
     * @param id       ID
     * @param name     名称
     * @param password 密码
     * @param roleIds  用户的角色
     */
    @Override
    public void update(Integer id, String name, String password, List<Integer> roleIds) {
        LocalUser user = localUserRepo.findOne(id);
        user.setName(name);
        if (StringUtils.hasText(password))
            user.setPassword(new Md5PasswordEncoder().encodePassword(password, ""));
        localUserRepo.save(user);
        Set<LocalRole> roles = new HashSet<>(capacity(roleIds.size()));
        roles.addAll(localRoleRepo.findAll(roleIds));
        user.setRoles(roles);
        localUserRepo.save(user);
    }

    /**
     * 删除
     *
     * @param id ID
     */
    @Override
    public void remove(Integer id) {
        LocalUser localUser = localUserRepo.findOne(id);
        if (null == localUser)
            throw new BusinessException(30000, "用户不存在！");
        localUserRepo.delete(localUser);
    }
}
