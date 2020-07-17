package com.nvke.restfulcrud.config;

import com.nvke.restfulcrud.component.LoginHandlerInterceptor;
import com.nvke.restfulcrud.component.MyLocaleResovler;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author nvke
 * @create 2020-07-16-17:02
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        //浏览器发送 /nvke 请求来到 success
//        registry.addViewController("/nvke").setViewName("success");
//    }

    //所有的WebMvcConfigurer组件都会一起起作用
    @Bean//将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            //视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
            //拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //SpringBoot未做静态资源映射，所以需要处理静态资源的访问
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                        excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");//拦截除了excludePathPatterns外的所有请求
            }
        };
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResovler();
    }
}
