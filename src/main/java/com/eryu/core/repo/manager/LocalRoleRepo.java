package com.eryu.core.repo.manager;

import com.eryu.core.entity.po.manager.LocalRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LocalRoleRepo extends JpaRepository<LocalRole, Integer> {

    /**
     * 初始化时获取超级管理员
     *
     * @param name 超级管理员
     * @return 超级管理员用户
     */
    Collection<? extends LocalRole> findByName(String name);
}
