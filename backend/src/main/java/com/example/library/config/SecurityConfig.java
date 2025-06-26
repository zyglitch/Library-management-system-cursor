package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 关闭CSRF
            .authorizeRequests()
                .antMatchers("/**").permitAll() // 允许所有接口访问
            .and()
            .formLogin().disable(); // 禁用默认表单登录
        return http.build();
    }
} 