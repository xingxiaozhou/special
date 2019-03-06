package cn.vp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模板映射
 */
@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("login");
       // registry.addViewController("/login.html").setViewName("login");

//        registry.addViewController("/list.html").setViewName("list");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//
//    }


    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                if (source != null&&!source.equals("")) {
                    try {
                        date = sdf.parse(source);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return date;
                }
                return null;
            }
        };
    }

}
