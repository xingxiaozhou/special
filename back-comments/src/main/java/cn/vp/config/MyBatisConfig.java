package cn.vp.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis映射类
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                System.err.println("进入mybatisConfig");
                //-自动使用驼峰命名属性映射字段   userId    user_id
                configuration.setMapUnderscoreToCamelCase(true);
                //使用列别名替换列名 select user as User
                configuration.setUseColumnLabel(true);
            }
        };
    }


}
