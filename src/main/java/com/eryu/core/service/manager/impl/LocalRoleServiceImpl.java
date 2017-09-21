package com.eryu.core.service.manager.impl;

import com.eryu.common.helper.MapHelper;
import com.eryu.common.helper.ParamHelper;
import com.eryu.core.entity.po.manager.LocalPrivilege;
import com.eryu.core.entity.po.manager.LocalRole;
import com.eryu.core.repo.manager.LocalPrivilegeRepo;
import com.eryu.core.repo.manager.LocalRoleRepo;
import com.eryu.core.service.manager.LocalRoleService;
import com.eryu.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 角色
 * Created by yangtao on 2017/7/22.
 */
@Service
public class LocalRoleServiceImpl implements LocalRoleService, MapHelper, ParamHelper {

    @Resource
    private LocalRoleRepo localRoleRepo;

    @Resource
    private LocalPrivilegeRepo localPrivilegeRepo;

    /**
     * 初始化
     */
    @Override
    public void init() {
        if (localRoleRepo.count() > 0)
            return;
        LocalRole role = new LocalRole();
        role.setName("超级管理员");
        localRoleRepo.save(role);
        Set<LocalPrivilege> privileges = new HashSet<>();
        privileges.addAll(localPrivilegeRepo.findByModel("admin"));
        role.setPrivileges(privileges);
        localRoleRepo.save(role);
    }

    /**
     * 列表
     *
     * @return List<ExpressCompany>
     */
    @Override
    public List<?> list() {
        return localRoleRepo.findAll();
    }

    /**
     * 添加
     *
     * @param name         名称
     * @param privilegeIds 权限的ID
     */
    @Override
    public void create(String name, List<Integer> privilegeIds) {
        LocalRole role = new LocalRole();
        role.setName(name);
        localRoleRepo.save(role);
        Set<LocalPrivilege> privileges = new HashSet<>(capacity(privilegeIds.size()));
        privileges.addAll(localPrivilegeRepo.findAll(privilegeIds));
        privileges.forEach(privilege -> privilege.setChildren(null));
        role.setPrivileges(privileges);
        localRoleRepo.save(role);
    }

    /**
     * 修改
     *
     * @param id           ID
     * @param privilegeIds 权限的ID
     */
    @Override
    public void update(Integer id, String name, List<Integer> privilegeIds) {
        LocalRole role = localRoleRepo.findOne(id);
        empty(role, "角色不存在！");
        role.setName(name);
        //更新权限
        Set<LocalPrivilege> privileges = new HashSet<>(capacity(privilegeIds.size()));
        privileges.addAll(localPrivilegeRepo.findAll(privilegeIds));
        privileges.forEach(privilege -> privilege.setChildren(null));
        role.setPrivileges(privileges);
        localRoleRepo.save(role);
    }

    /**
     * 删除
     *
     * @param id ID
     */
    @Override
    @Transactional
    public void remove(Integer id) {
        LocalRole role = localRoleRepo.findOne(id);
        if (null == role)
            throw new BusinessException(30000, "角色不存在！");
        localRoleRepo.delete(role);
    }
}
