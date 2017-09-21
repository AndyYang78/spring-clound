package com.eryu.core.repo.manager;

import com.eryu.core.entity.po.manager.LocalPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LocalPrivilegeRepo extends JpaRepository<LocalPrivilege, Integer> {
    /**
     * 获取根权限
     *
     * @param isRoot 是否根权限
     * @return 权限列表
     */
    List<LocalPrivilege> findByIsRoot(boolean isRoot);

    /**
     */
    Collection<? extends LocalPrivilege> findByModel(String admin);
}
