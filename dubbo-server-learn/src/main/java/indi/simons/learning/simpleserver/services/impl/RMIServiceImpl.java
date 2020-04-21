package indi.simons.learning.simpleserver.services.impl;

import indi.simons.learning.simpleserver.services.IRMIService;

/**
 * @author Simons
 */
public class RMIServiceImpl implements IRMIService{
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
