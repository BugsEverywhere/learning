package indi.simon.learning.simpleserver.services.impl;

import indi.simon.learning.simpleserver.services.IRMIService;

/**
 * @author Simons
 */
public class RMIServiceImpl implements IRMIService {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
