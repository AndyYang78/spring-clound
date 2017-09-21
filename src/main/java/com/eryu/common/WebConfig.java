package com.eryu.common;

import com.eryu.core.entity.po.manager.LocalPrivilege;
import com.eryu.core.service.manager.LocalPrivilegeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Web配置
 * Created by yangtao on 2017/8/7.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Resource
    private LocalPrivilegeService localPrivilegeService;

    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
    }

    /**
     * URL 映射配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/404").setStatusCode(HttpStatus.NOT_FOUND).setViewName("/error/404");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        for (LocalPrivilege localPrivilege : localPrivilegeService.listAll()) {
            if (!"admin".equals(localPrivilege.getModel()))
                registry.addViewController(localPrivilege.getPath().replace('-', '/')).setViewName(localPrivilege.getViewName());
        }
    }
}