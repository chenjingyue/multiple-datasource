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
@MapperScan(basePackages = {"com.example.my_springboot_learn.mapper.test2"},sqlSessionFactoryRef = "test2SqlSessionFactory")
//这里一定要注意，这个basePackages是你的mapper接口及service所在的包名，
// 而下面的红线所标注的classpath是mapper.xml所在的位置，这个xml是配置文件，处在resources里，他的路径也要格外区分开。

public class DatasourceConfig {

    //  DataSource其中一种配置，对应spring.datasource.test2.url
    @Primary
    @Bean(name="dataSourceProperties")
    @Qualifier("dataSourceProperties")
    @ConfigurationProperties(prefix ="spring.datasource.test2")
    public DataSourceProperties primaryDataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean(name="test2DataSource")
    //下面的注解作用就是从application.properties中读取以这个字符串开头的那些配置，设置为数据源的配置
    @ConfigurationProperties(prefix ="spring.datasource.test2")
    public DataSource primaryDataSource(){
        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
    }

//  DataSource其中一种配置，对应spring.datasource.test2.jdbc-url
//    @Bean(name="test2DataSource")
//    //下面的注解作用就是从application.properties中读取以这个字符串开头的那些配置，设置为数据源的配置
//    @ConfigurationProperties(prefix ="spring.datasource.test2")
//    public DataSource testDataSource(){
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name="test2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/test2/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "test2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
