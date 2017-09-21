package com.eryu.common.utils;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * 配置文件的大小 解决MultipartException
 * Created by trpoubleMan on 2017/8/2.
 */
public class CommonConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024L * 1024L*10);
        return factory.createMultipartConfig();
    }
}
