package cn.vp.congif;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 模板映射配置
 */
@Configuration
public class MyConfigs extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/userLogin.htm").setViewName("index");
        registry.addViewController("/address.htm").setViewName("address");
        registry.addViewController("/signUp.htm").setViewName("signUp");
        registry.addViewController("/findPwd.htm").setViewName("findPwd");
        registry.addViewController("/updatePwd.htm").setViewName("updatePwd");
        registry.addViewController("/addressAdd.htm").setViewName("addressAdd");
        registry.addViewController("/addressUpdate.htm").setViewName("addressUpdate");
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
