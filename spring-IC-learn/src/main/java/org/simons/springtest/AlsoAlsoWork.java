package org.simons.springtest;

import org.simons.springtest.service.WithNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

/**
 * Created by Simons on 2017/5/11.
 */
@ComponentScan
public class AlsoAlsoWork {

    @Autowired
    private WithNameService withNameService;

    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(AlsoAlsoWork.class);

        AlsoAlsoWork alsoAlsoWork = context.getBean(AlsoAlsoWork.class);

        alsoAlsoWork.getService().sayHello();

    }

    private WithNameService getService(){

        return withNameService;

    }


}
