package indi.simons.simpleserver.services.impl;

import indi.simons.simpleserver.services.IHelloService;

/**
 * @author Simons
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }

}
