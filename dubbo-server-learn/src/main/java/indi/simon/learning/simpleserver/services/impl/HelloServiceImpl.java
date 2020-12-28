package indi.simon.learning.simpleserver.services.impl;

import indi.simon.learning.simpleserver.services.IHelloService;

/**
 * @author Simons
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }

}
