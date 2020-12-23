package com.first.memorandum.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.first.memorandum.dto.JdbcProperties;
import org.apache.ibatis.logging.nologging.NoLoggingImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 自定义数据源配置
 */
@Configuration
@EnableConfigurationProperties(JdbcProperties.class)
public class SqlSessionFactoryConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;


    @Bean("myDataSource")
    public DataSource myDataSource(JdbcProperties properties){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(properties.getType());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUserName());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean("mySqlSessionFactory")
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("myDataSource") DataSource myDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setLogImpl(StdOutImpl.class);//开始日志输出
        configuration.setLogImpl(NoLoggingImpl.class);//不需要日志输出
        configuration.setMapUnderscoreToCamelCase(false);//不开启驼峰命令
//        configuration.setCallSettersOnNulls(true);// 开启在属性为null也调用setter方法
        factoryBean.setConfiguration(configuration);
        factoryBean.setDataSource(myDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        return factoryBean.getObject();
    }

//    @Bean("mySqlSessionFactory")
//    public SqlSessionFactory mySqlSessionFactory(@Qualifier("mySqlSessionFactoryBean") SqlSessionFactoryBean mySqlSessionFactoryBean) throws Exception{
//        return mySqlSessionFactoryBean.getObject();
//    }

//    @Bean
//    public DataSourceTransactionManager myDataSourceTransactionManager(@Qualifier("myDataSource")DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean("mySqlSessionTemplate")
    public SqlSessionTemplate mySqlSessionTemplate(@Qualifier("mySqlSessionFactory") SqlSessionFactory mySqlSessionFactory) {
        return new SqlSessionTemplate(mySqlSessionFactory);
    }

}
