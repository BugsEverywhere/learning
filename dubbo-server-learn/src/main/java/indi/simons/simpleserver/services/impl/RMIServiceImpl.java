package indi.simons.simpleserver.services.impl;

import indi.simons.simpleserver.services.IRMIService;

/**
 * @author Simons
 */
public class RMIServiceImpl implements IRMIService{
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
