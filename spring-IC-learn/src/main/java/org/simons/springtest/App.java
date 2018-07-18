package org.simons.springtest;

import org.simons.springtest.service.TestInjectService;
import org.simons.springtest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Hello world!
 *
 */

@ComponentScan
public class App 
{

    @Autowired
    private TestService testService;

    @Autowired
    private TestInjectService testInjectService;


    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        App app = context.getBean(App.class);

        app.testService();

    }

    public void testService(){

        testInjectService.injectTest();

    }


}
