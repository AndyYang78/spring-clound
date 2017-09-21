package com.eryu.core.service.manager.impl;

import com.eryu.common.security.DefaultPrivilege;
import com.eryu.core.entity.po.manager.LocalPrivilege;
import com.eryu.core.repo.manager.LocalPrivilegeRepo;
import com.eryu.core.service.manager.LocalPrivilegeService;
import com.eryu.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 权限
 * Created by yangtao on 2017/7/22.
 */
@Slf4j
@Service
public class LocalPrivilegeServiceImpl implements LocalPrivilegeService {

    @Resource
    private LocalPrivilegeRepo localPrivilegeRepo;

    /**
     * 权限初始化
     */
    @Override
    public void init() {
        if (localPrivilegeRepo.count() < 1) {
            log.info("初始化权限...");
            for (LocalPrivilege localPrivilege : new DefaultPrivilege().DEFAULT_PRIVILEGES) {
                LocalPrivilege parent = localPrivilege.getParent();
                localPrivilege.setParent(null);
                localPrivilegeRepo.saveAndFlush(localPrivilege);
                if (null != parent) {
                    localPrivilege.setParent(parent);
                    localPrivilegeRepo.saveAndFlush(localPrivilege);
                }
            }
        }
    }

    /**
     * 列表
     *
     * @return List<ExpressCompany>
     */
    @Override
    public List<LocalPrivilege> list() {
        return localPrivilegeRepo.findByIsRoot(true);
    }

    /**
     * 获取列表
     *
     * @return List<LocalPrivilege>
     */
    @Override
    public List<LocalPrivilege> listAll() {
        return localPrivilegeRepo.findAll();
    }

    /**
     * 新增权限
     *
     * @param name     权限名称
     * @param model    权限类型
     * @param page     权限页面
     * @param point    权限点
     * @param parentId 父权限ID
     */
    @Override
    public void create(String name, String model, String page, String point, Integer parentId) {
        //为顶级权限
        LocalPrivilege privilege = new LocalPrivilege();
        if (parentId == null) {
            privilege.setName(name);
            privilege.setModel(model);
            privilege.setIsRoot(true);
            privilege.setPath("/view/" + model);
            localPrivilegeRepo.save(privilege);
        } else {
            LocalPrivilege parent = localPrivilegeRepo.findOne(parentId);
            if (null == parent)
                throw new BusinessException(30000, "父权限不存在！");
            privilege.setName(name);
            privilege.setModel(model);
            privilege.setIsRoot(false);
            privilege.setPath(("/view/" + model + "/" + page).replace("-", "/"));
            privilege.setParent(parent);
            localPrivilegeRepo.save(privilege);
        }
    }

    /**
     * 删除
     *
     * @param id ID
     */
    @Override
    public void remove(Integer id) {
        LocalPrivilege privilege = localPrivilegeRepo.findOne(id);
        if (null == privilege)
            throw new BusinessException(30000, "权限不存在！");
        localPrivilegeRepo.delete(privilege);
    }
}
