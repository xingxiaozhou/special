package cn.vp.config;


import cn.vp.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 配置
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(*//*"/login","/index",*//* "/index.do",*//* "/",*//* "/login.html", "/static/**");*/
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/login.html", "/index.do", "/index.html");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.err.println("1112112222");
         registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/useraddress.html").setViewName("useraddress");
//        /*registry.addViewController("/product.htm").setViewName("product");
//        registry.addViewController("/productAdd.htm").setViewName("productAdd");
//        registry.addViewController("/list.html").setViewName("list");
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//
//    }
//

}
