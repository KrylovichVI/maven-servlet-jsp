package com.krylovichVI.controller.config;

import com.krylovichVI.controller.controllers.*;
import com.krylovichVI.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private ServiceConfig serviceConfig;

    @Autowired
    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public StartController startController(){
        return new StartController();
    }

    @Bean
    public LoginController loginController(){ return new LoginController(
            serviceConfig.authUserService(),
            serviceConfig.blackListService(),
            serviceConfig.securityService());
    }

    @Bean
    public PageController pageController(){ return new PageController(serviceConfig.bookService());}

    @Bean
    public UserSettingsController userSettingsController(){ return new UserSettingsController(serviceConfig.userService());}

    @Bean
    public UserListController userListController(){
        return new UserListController(serviceConfig.authUserService(), serviceConfig.blackListService());
    }

    @Bean
    public BlackListController blackListController(){
        return new BlackListController(serviceConfig.authUserService(), serviceConfig.blackListService());
    }

    @Bean
    public RegistrationController registrationController(){ return new RegistrationController(serviceConfig.authUserService());}

    @Bean
    public ViewResolver freemarkerViewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setCache(true);
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftl");

        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/");
        return freeMarkerConfigurer;
    }
}
