package com.krylovichVI.controller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
    @Value("D:/IT_Academy/Library/uploads")
    private String uploadPath;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return multipartResolver;
    }

    @Bean
    public ViewResolver freemarkerViewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
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

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/messages");
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("en"));
        resolver.setCookieName("LocaleCookie");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }
}
