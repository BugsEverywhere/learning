package org.simons.springtest.service;

import org.springframework.stereotype.Service;

/**
 * Created by Simons on 2017/5/11.
 */
@Service("WithNameService")
public class WithNameService {

    public void sayHello(){

        System.out.println("Hello World");

    }
}
