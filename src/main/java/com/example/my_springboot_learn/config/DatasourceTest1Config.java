package com.example.my_springboot_learn.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.my_springboot_learn.mapper.test1"},sqlSessionFactoryRef = "test1SqlSessionFactory")
//这里一定要注意，这个basePackages是你的mapper接口及service所在的包名，
// 而下面的红线所标注的classpath是mapper.xml所在的位置，这个xml是配置文件，处在resources里，他的路径也要格外区分开。

public class DatasourceTest1Config {


/*配置第二个数据源*/
    //  DataSource其中一种配置，对应spring.datasource.test2.jdbc-url
    @Bean(name="test1DataSource")
    //下面的注解作用就是从application.properties中读取以这个字符串开头的那些配置，设置为数据源的配置
    @ConfigurationProperties(prefix ="spring.datasource.test1")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="test1SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test1/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "test1TransactionManager")
    public DataSourceTransactionManager test1TransactionManager(@Qualifier("test1DataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name="test1SqlSessionTemplate")
    public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("test1SqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
