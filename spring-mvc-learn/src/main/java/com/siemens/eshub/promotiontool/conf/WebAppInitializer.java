package com.siemens.eshub.promotiontool.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Chen Zhuo on 2017/6/19.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //用来载入程序中其余的 beans，例如一些中间层和数据层组件，完成的是程序后端功能
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    //设置 DispatcherServlet 通过 WebConfig 配置类来完成 Spring 上下文和 bean 的加载
    @Override
    protected Class<?>[] getServletConfigClasses() {return new Class<?>[] { WebConfig.class };}

    //指定本 DispatcherServlet 为默认的Servlet，也可以配置其他Servlet映射来分发http request
    @Override
    protected String[] getServletMappings() {    //将DispatcherServlet映射到"/"
        return new String[] { "/" };
    }

}
