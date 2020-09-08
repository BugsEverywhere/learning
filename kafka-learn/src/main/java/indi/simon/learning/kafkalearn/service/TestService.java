package indi.simon.learning.kafkalearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Service
public class TestService {

    @Autowired
    private IServiceInterface serviceInterface;

    public String sayHello(){
        return serviceInterface.sayHelloAgain();
    }

}
