package cn.vp.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库数据源配置类
 */
@Configuration
public class DataSourcesConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource getDataSources() {
        return new DruidDataSource();
    }

//    private static final String DB_PREFIX = "spring.datasource";




    /**
     * 配置druid后台管理的servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "admin");
        initParams.put("allow", "");//默认就是允许所有访问
        initParams.put("deny", "192.168.15.21");//禁止访问的ip
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * 配置druid后台管理的filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        //设置不拦截的路径  *.cs *.js    /druid/*
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        //设置filter拦截 那些请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }


//    //解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
//    @ConfigurationProperties(prefix = DB_PREFIX)
//    class IDataSourceProperties {
//        private String url;
//        private String username;
//        private String password;
//        private String driverClassName;
//        private int initialSize;
//        private int minIdle;
//        private int maxActive;
//        private int maxWait;
//        private int timeBetweenEvictionRunsMillis;
//        private int minEvictableIdleTimeMillis;
//        private String validationQuery;
//        private boolean testWhileIdle;
//        private boolean testOnBorrow;
//        private boolean testOnReturn;
//        private boolean poolPreparedStatements;
//        private int maxPoolPreparedStatementPerConnectionSize;
//        private boolean useGlobalDataSourceStat;
//        private String filters;
//        private String connectionProperties;
//
//        @Bean     //声明其为Bean实例
//        @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//        public DataSource dataSource() {
//            DruidDataSource datasource = new DruidDataSource();
//            datasource.setUrl(url);
//            datasource.setUsername(username);
//            datasource.setPassword(password);
//            datasource.setDriverClassName(driverClassName);
//
//            //configuration
//            datasource.setInitialSize(initialSize);
//            datasource.setMinIdle(minIdle);
//            datasource.setMaxActive(maxActive);
//            datasource.setMaxWait(maxWait);
//            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//            datasource.setValidationQuery(validationQuery);
//            datasource.setTestWhileIdle(testWhileIdle);
//            datasource.setTestOnBorrow(testOnBorrow);
//            datasource.setTestOnReturn(testOnReturn);
//            datasource.setPoolPreparedStatements(poolPreparedStatements);
//            datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
//            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//            try {
//                datasource.setFilters(filters);
//            } catch (SQLException e) {
//                System.err.println("druid configuration initialization filter: " + e);
//            }
//            datasource.setConnectionProperties(connectionProperties);
//            return datasource;
//        }
//
//        public boolean isUseGlobalDataSourceStat() {
//            return useGlobalDataSourceStat;
//        }
//
//        public void setUseGlobalDataSourceStat(boolean useGlobalDataSourceStat) {
//            this.useGlobalDataSourceStat = useGlobalDataSourceStat;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public void setPassword(String password) {
//            this.password = password;
//        }
//
//        public String getDriverClassName() {
//            return driverClassName;
//        }
//
//        public void setDriverClassName(String driverClassName) {
//            this.driverClassName = driverClassName;
//        }
//
//        public int getInitialSize() {
//            return initialSize;
//        }
//
//        public void setInitialSize(int initialSize) {
//            this.initialSize = initialSize;
//        }
//
//        public int getMinIdle() {
//            return minIdle;
//        }
//
//        public void setMinIdle(int minIdle) {
//            this.minIdle = minIdle;
//        }
//
//        public int getMaxActive() {
//            return maxActive;
//        }
//
//        public void setMaxActive(int maxActive) {
//            this.maxActive = maxActive;
//        }
//
//        public int getMaxWait() {
//            return maxWait;
//        }
//
//        public void setMaxWait(int maxWait) {
//            this.maxWait = maxWait;
//        }
//
//        public int getTimeBetweenEvictionRunsMillis() {
//            return timeBetweenEvictionRunsMillis;
//        }
//
//        public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
//            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
//        }
//
//        public int getMinEvictableIdleTimeMillis() {
//            return minEvictableIdleTimeMillis;
//        }
//
//        public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
//            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
//        }
//
//        public String getValidationQuery() {
//            return validationQuery;
//        }
//
//        public void setValidationQuery(String validationQuery) {
//            this.validationQuery = validationQuery;
//        }
//
//        public boolean isTestWhileIdle() {
//            return testWhileIdle;
//        }
//
//        public void setTestWhileIdle(boolean testWhileIdle) {
//            this.testWhileIdle = testWhileIdle;
//        }
//
//        public boolean isTestOnBorrow() {
//            return testOnBorrow;
//        }
//
//        public void setTestOnBorrow(boolean testOnBorrow) {
//            this.testOnBorrow = testOnBorrow;
//        }
//
//        public boolean isTestOnReturn() {
//            return testOnReturn;
//        }
//
//        public void setTestOnReturn(boolean testOnReturn) {
//            this.testOnReturn = testOnReturn;
//        }
//
//        public boolean isPoolPreparedStatements() {
//            return poolPreparedStatements;
//        }
//
//        public void setPoolPreparedStatements(boolean poolPreparedStatements) {
//            this.poolPreparedStatements = poolPreparedStatements;
//        }
//
//        public int getMaxPoolPreparedStatementPerConnectionSize() {
//            return maxPoolPreparedStatementPerConnectionSize;
//        }
//
//        public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
//            this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
//        }
//
//        public String getFilters() {
//            return filters;
//        }
//
//        public void setFilters(String filters) {
//            this.filters = filters;
//        }
//
//        public String getConnectionProperties() {
//            return connectionProperties;
//        }
//
//        public void setConnectionProperties(String connectionProperties) {
//            this.connectionProperties = connectionProperties;
//        }
//    }

}



