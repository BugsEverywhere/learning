package com.siemens.eshub.promotiontool.mapper;

import com.siemens.eshub.promotiontool.conf.MybatisConf;
import com.siemens.eshub.promotiontool.model.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Simons on 2017/6/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConf.class)
public class UsersMapperTest extends TestCase {

    @Autowired
    private UserMapper usersMapper;


    @Test
    public void insertTest(){

        User users = new User();
//        users = usersMapper.selectByPrimaryKey("zhuo.chen");
//        System.out.println(users.getPassword());

//        users.setEmail("asdasdasdasdasd");
//        users.setPassword("123456");
//        //users.setUserlevel(1);
//        users.setUsername("hahahaha");
//
//        int i = usersMapper.insert(users);
//
//        System.out.println(i);

    }


}