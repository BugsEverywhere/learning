package indi.simon.learning.simpleserver;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.concurrent.TimeUnit;

/**
 * @author Simons
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"classpath:spring-application-context.xml"});
        context.start();

        while(true){
            TimeUnit.MILLISECONDS.sleep(1000L);
        }
    }
}