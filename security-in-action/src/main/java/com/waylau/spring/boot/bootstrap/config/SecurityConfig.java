package com.waylau.spring.boot.bootstrap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by wj on 2018/2/26.
 */
@EnableWebSecurity //启用web安全认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //设置拦截策略
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**","/js/**","/fonts/**","/index").permitAll() //都可以访问
        .antMatchers("/users/**").hasRole("ADMIN")//需要相应角色才能昂问
        .and().formLogin()//基于Form表单登陆验证
        .loginPage("/login").failureUrl("/login-error"); //自定义登陆界面
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //认证信息存储在内存中
        auth.inMemoryAuthentication().withUser("ashercode").password("123456").roles("ADMIN");
    }
}
