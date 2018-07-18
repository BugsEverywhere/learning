package com.siemens.eshub.promotiontool.service;

import com.siemens.eshub.promotiontool.conf.RootConfig;
import com.siemens.eshub.promotiontool.model.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Zhuo on 2017/6/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class LoginServiceTest extends TestCase {

    @Autowired
    private LoginService loginService;

    @Test
    public void registerTest(){

//        User user = new User();
//
//        user.setEmail("rrrr");
//        user.setPassword("asdasdasd");
//        user.setUserlevel(1);
//        user.setUsername("hahahah");
//
//        int i = loginService.registerUser(user);
//
//        System.out.println(i);

    }

}