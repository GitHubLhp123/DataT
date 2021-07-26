package com.datat.config;

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
 * @author mlhp1
 * @date 2021-07-26 17:44:25
 * @desc 配置数据源连接池
 */
@Configuration
public class DruidConfig {
    /**
     * druid
     *
     * @return DataSource
     * @desc 取出配置文件中的‘spring.datasource’进行注入数据
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * statViewServlet
     *
     * @return ServletRegistrationBean
     * @desc 配置Druid的监控，配置一个管理后台的Servlet
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        //默认就是允许所有访问
        initParams.put("allow", "");
        initParams.put("deny", "192.168.15.21");
        bean.setInitParameters(initParams);
        return bean;
    }

    /**
     * webStatFilter
     *
     * @return FilterRegistrationBean
     * @desc 配置一个web监控的filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}