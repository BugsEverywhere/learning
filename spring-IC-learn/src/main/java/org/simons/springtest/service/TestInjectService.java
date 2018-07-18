package org.simons.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Simons on 2017/4/26.
 */
@Service
public class TestInjectService {

    @Autowired
    private TestService testService;

    public void injectTest(){

        testService.printTest();

    }



}
