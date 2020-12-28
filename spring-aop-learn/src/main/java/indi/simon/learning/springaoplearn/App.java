package indi.simon.learning.springaoplearn;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author chenzhuo(zhiyue)
 */
public class App {

    public static void main(String[] args) {

        // 1. 创建代理工厂
        ProxyFactory factory = new ProxyFactory();
        // 2. 设置代理目标对象
        factory.setTarget(new UserServiceImpl());
        // 3. 设置接口
        factory.setInterfaces(UserService.class);
        // 4. 设置增强
        factory.addAdvice(new LogBeforeAdvice());
        factory.addAdvice(new LogAfterAdvice());
        // 5. 获取代理对象
        UserService userService = (UserService) factory.getProxy();

        userService.findUser();

    }

    // 前置增强
    private static class LogBeforeAdvice implements MethodBeforeAdvice {

        public void before(Method method, Object[] args, Object target) {
            System.out.println("before log...");
        }
    }

    // 后置增强
    private static class LogAfterAdvice implements AfterReturningAdvice {

        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
            System.out.println("after log...");
        }
    }



}
