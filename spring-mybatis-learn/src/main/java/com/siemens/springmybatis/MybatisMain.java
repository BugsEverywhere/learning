package com.siemens.springmybatis;

import com.siemens.springmybatis.conf.MybatisConf;
import com.siemens.springmybatis.model.SimpleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Simons on 2017/5/3.
 */

public class MybatisMain {

    @Autowired
    private TestService testService;

    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisConf.class);

        MybatisMain mybatisMain = (MybatisMain) context.getBean("MybatisMain");

        TestService testService = mybatisMain.getTestService();

        SimpleModel historical = testService.selectFirstModel("1");

        System.out.println();

    }

    private TestService getTestService(){

        return this.testService;

    }


}
