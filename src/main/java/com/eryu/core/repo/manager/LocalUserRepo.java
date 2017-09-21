package com.eryu.core.repo.manager;

import com.eryu.core.entity.po.manager.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalUserRepo extends JpaRepository<LocalUser, Integer> {

    LocalUser findUserByName(String userName);
}
