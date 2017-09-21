package com.eryu.common.security;

import com.eryu.core.entity.po.manager.LocalUser;
import com.eryu.core.service.manager.LocalUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class CustomUserDetailsService implements UserDetailsService {

    @Resource  //数据库服务类
    private LocalUserService localUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SUser中的email作为用户名:
        LocalUser user = localUserService.findUserByName(userName); //code8
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found !");
        }
        return new SecurityUser(user);
    }

}
