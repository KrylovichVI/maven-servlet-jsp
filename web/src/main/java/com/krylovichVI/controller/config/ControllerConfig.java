package com.krylovichVI.controller.config;

import com.krylovichVI.controller.controllers.*;
import com.krylovichVI.service.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    private ServiceConfig serviceConfig;

    @Autowired
    public ControllerConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    public BlackListController blackListController(){
        return new BlackListController(serviceConfig.authUserService(), serviceConfig.blackListService());
    }

    @Bean
    public LoginController loginController(){ return new LoginController(
            serviceConfig.authUserService(),
            serviceConfig.blackListService(),
            serviceConfig.securityService());
    }

    @Bean
    public LogoutController logoutController(){ return new LogoutController();}

    @Bean
    public OrdersAdminController ordersAdminController(){
        return new OrdersAdminController(serviceConfig.orderService());
    }

    @Bean
    public OrdersUserController ordersUserController(){
        return new OrdersUserController(
                serviceConfig.authUserService(),
                serviceConfig.orderService(),
                serviceConfig.bookService()
                );
    }

    @Bean
    public RedirectController redirectController(){
        return new RedirectController();
    }

    @Bean
    public MainPageController pageController(){
        return new MainPageController(serviceConfig.bookService());
    }

    @Bean
    public RegistrationController registrationController(){ return new RegistrationController(serviceConfig.authUserService());}

    @Bean
    public StartController startController(){
        return new StartController();
    }

    @Bean
    public UserListController userListController(){
        return new UserListController(serviceConfig.authUserService(), serviceConfig.blackListService());
    }

    @Bean
    public UserSettingsController userSettingsController(){ return new UserSettingsController(serviceConfig.userService());}



}
