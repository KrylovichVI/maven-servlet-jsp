package com.krylovichVI.controller.config;

import com.krylovichVI.controller.handler.CustomAccessDeniedHandler;
import com.krylovichVI.controller.handler.CustomHttp403ForbiddenEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public CustomHttp403ForbiddenEntryPoint customHttp403ForbiddenEntryPoint(){
        return new CustomHttp403ForbiddenEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/registration", "/error/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(customHttp403ForbiddenEntryPoint());

    }
}
