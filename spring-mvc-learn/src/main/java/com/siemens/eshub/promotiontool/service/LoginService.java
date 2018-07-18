package com.siemens.eshub.promotiontool.service;

import com.siemens.eshub.promotiontool.mapper.UserMapper;
import com.siemens.eshub.promotiontool.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chen Zhuo on 2017/6/21.
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    public User selectByEmail(String email){

        return userMapper.selectByPrimaryKey(email);

    }

    public int registerUser(User user){

        if(user.getEmail()!=null&&user.getPassword()!=null&&user.getUserlevel()!=null){

            return userMapper.insert(user);

        }

        return 0;

    }

}
