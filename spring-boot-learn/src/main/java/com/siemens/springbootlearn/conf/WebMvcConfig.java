package com.siemens.springbootlearn.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * zhuo.chen@siemens.com
 * Created by chenzhuo on 2016/10/17.
 */
@Configuration
@MapperScan("com.siemens.springbootlearn.mapper")
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/html/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/html/images/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/html/assets/");
        registry.addResourceHandler("/html/**").addResourceLocations("classpath:/html/");

    }
}
