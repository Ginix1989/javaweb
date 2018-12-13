package com.example.javaweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 自定义配置文件
 */
@Configuration

public class JavaWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //配置登录
            registry.addViewController("/login").setViewName("reg\\reg");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //将templates目录下的CSS、JS文件映射为静态资源，防止Spring把这些资源识别成thymeleaf模版
//        registry.addResourceHandler("/templates/**.js").addResourceLocations("classpath:/templates/");
//        registry.addResourceHandler("/templates/**.css").addResourceLocations("classpath:/templates/");
//        //其他静态资源
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
}
