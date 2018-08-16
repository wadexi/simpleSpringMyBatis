package com.study.springmybatis.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.cfg.PropertiesConfigSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataConfig {


    /**
     * 设置数据源
     * 设置数据源
     * @return
     * @throws IOException
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource() throws IOException, PropertyVetoException {
        Properties properties = new Properties();
//        正确的写法1
        properties.load(this.getClass().getResourceAsStream("/jdbc.properties"));
//        正确的写法2
//        properties.load(new ClassPathResource("/jdbc.properties").getInputStream());

//        错误的写法
//        properties.load(new ClassPathResource("classpath:jdbc.properties").getInputStream());

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("passwd"));
        return comboPooledDataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setConfigLocation(new ClassPathResource("/spring-mybatis.xml"));
//        错误的写法
//        sessionFactoryBean.setConfigLocation(new ClassPathResource("classpath:spring-mybatis.xml"));
        return sessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.study.springmybatis.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        return mapperScannerConfigurer;
    }

}
