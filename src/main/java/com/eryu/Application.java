package com.eryu;

import com.eryu.core.service.manager.LocalPrivilegeService;
import com.eryu.core.service.manager.LocalRoleService;
import com.eryu.core.service.manager.LocalUserService;
import feign.Request;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringCloudApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Request.Options feignOptions() {
        return new Request.Options(2000, 20000);
    }

    @Resource
    private LocalPrivilegeService localPrivilegeService;

    @Resource
    private LocalRoleService localRoleService;

    @Resource
    private LocalUserService localUserService;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            //初始化权限
            localPrivilegeService.init();
            //初始化角色
            localRoleService.init();
            //初始化用户
            localUserService.init();
        };
    }
}