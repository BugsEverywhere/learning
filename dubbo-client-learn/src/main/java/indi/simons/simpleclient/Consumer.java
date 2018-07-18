package indi.simons.simpleclient;

import indi.simons.simpleserver.services.IHelloService;
import indi.simons.simpleserver.services.IRMIService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Simons
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath:spring-application-context.xml"});
        context.start();
        // obtain proxy object for remote invocation
        IHelloService helloService = (IHelloService) context.getBean("helloService");
        // execute remote invocation
        String hello = helloService.sayHello("world");
        // show the result
        System.out.println(hello);


        // obtain proxy object for remote invocation
        IRMIService irmiService = (IRMIService) context.getBean("rmiService");
        // execute remote invocation
        String helloRmi = irmiService.sayHello("RMI");
        // show the result
        System.out.println(helloRmi);
    }
}