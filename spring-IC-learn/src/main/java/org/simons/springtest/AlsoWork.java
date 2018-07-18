package org.simons.springtest;

import org.simons.springtest.service.TestInjectService;
import org.simons.springtest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Simons on 2017/5/3.
 */
@Configuration
public class AlsoWork {

    @Autowired
    private TestService testService;

    @Autowired
    private TestInjectService testInjectService;

    @Bean(name = "AlsoWork")
    AlsoWork alsoWork(){
        return new AlsoWork();
    }

    @Bean(name = "TestService")
    TestService testService(){
        return new TestService();
    }

    @Bean(name = "TestInjectService")
    TestInjectService testInjectService(){
        return new TestInjectService();
    }



    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(AlsoWork.class);

        AlsoWork alsoWork = (AlsoWork) context.getBean("AlsoWork");

        alsoWork.testtestService();

    }

    public void testtestService(){

        testInjectService.injectTest();

    }
}
