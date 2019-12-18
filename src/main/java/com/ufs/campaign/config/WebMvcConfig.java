package com.ufs.campaign.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring mvc 配置
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 设置资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //全部静态资源,如果指定全部资源则全局异常捕获无法传递到自定义的error.html页面
        //registry.addResourceHandler("/webapp/**").addResourceLocations("classpath:/webapp/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
