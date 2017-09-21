package com.eryu.common;


import com.eryu.common.security.CustomUserDetailsService;
import com.eryu.core.entity.po.manager.LocalPrivilege;
import com.eryu.core.service.manager.LocalPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LocalPrivilegeService localPrivilegeService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //获取权限列表
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        for (LocalPrivilege localPrivilege : localPrivilegeService.listAll()) {
//            registry.antMatchers(localPrivilege.getPath()).access("hasRole('ADMIN') and hasRole('" + localPrivilege.getPrivilege() + "')");
            registry.antMatchers(localPrivilege.getPath().replace('-', '/')).hasAnyAuthority(localPrivilege.getRolePrivilege(), "ROLE_ADMIN");//.hasAnyRole(localPrivilege.getPrivilege(),"ADMIN");
        }
        //允许所有用户访问"/"和"/home"
        registry.antMatchers("/login", "/404", "/403").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                //登录成功后默认跳转到"/home"
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").failureForwardUrl("/login-error").permitAll()
                .and()
                //退出登录后的默认url是"/home"
                .logout().logoutSuccessUrl("/login").permitAll();
//                .and()
//                .csrf().disable();
    }


    /**
     * 静态资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/content/**", "/common/**");
    }

    /**
     * 认证
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

}