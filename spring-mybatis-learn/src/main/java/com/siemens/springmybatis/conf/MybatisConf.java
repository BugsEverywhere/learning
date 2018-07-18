package com.siemens.springmybatis.conf;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.siemens.springmybatis.MybatisMain;
import com.siemens.springmybatis.TestService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * Created by Simons on 2017/5/22.
 */
@Configuration
@MapperScan("com.siemens.springmybatis.mapper")
public class MybatisConf {

    @Bean
    public DataSource dataSource() {
        return new ComboPooledDataSource("myc3p0xml");
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name="TestService")
    TestService testService(){
        return new TestService();
    }

    @Bean(name = "MybatisMain")
    MybatisMain mybatisMain(){
        return new MybatisMain();
    }


//    @Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }

//    @Bean
//    public HistoricalMapper historicalMapper() throws Exception {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
//        return sessionTemplate.getMapper(HistoricalMapper.class);
//    }

}
