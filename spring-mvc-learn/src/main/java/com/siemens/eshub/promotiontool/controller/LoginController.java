package com.siemens.eshub.promotiontool.controller;

import com.siemens.eshub.promotiontool.model.User;
import com.siemens.eshub.promotiontool.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.siemens.eshub.promotiontool.singleton.CommonModelMapParms.*;
import static com.siemens.eshub.promotiontool.singleton.LogParameter.*;

/**
 * Created by Chen Zhuo on 2017/6/19.
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    static final int SUCCESS = 1;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login/{email}/{psw}")
    @ResponseBody
    public ModelMap login(@PathVariable String email,@PathVariable String psw) {

        ModelMap result = new ModelMap();

        //如果登录邮箱或密码为空，则直接返回登录失败信息
        if(email==null||psw==null){

            result.put(IS_SUCCESS,false);
            result.put(MSG, USERNAME_OR_PWD_CAN_NOT_BE_NULL);

        }else{

            User user = loginService.selectByEmail(email);

            //是否查无此人
            if (null==user){
                result.put(IS_SUCCESS,false);
                result.put(MSG,NO_SUCH_USER);
            }else {

                try {
                    //密码正确与否
                    if (psw.equals(user.getPassword())) {

                        result.put(IS_SUCCESS, true);
                        result.put(MSG, LOGIN_SUCCESS);
                        result.put(USER_LEVEL, user.getUserlevel());

                    } else {

                        result.put(IS_SUCCESS, false);
                        result.put(MSG, USERNAME_OR_PWS_IS_WRONG);

                    }
                }catch(Exception e){

                }
            }
        }

        return result;

    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public ModelMap register(HttpServletRequest request){

        ModelMap result = new ModelMap();

        User newUser = new User();

        String email = request.getParameter("email");
        String psw = request.getParameter("password");
        Integer level = Integer.parseInt(request.getParameter("level"));
        String userName = request.getParameter("username");

        newUser.setEmail(email);
        newUser.setPassword(psw);
        newUser.setUserlevel(level);
        newUser.setUsername(userName);

        User user = loginService.selectByEmail(email);

        //已经有此用户了
        if(user!=null){

            result.put(IS_SUCCESS, false);
            result.put(MSG, USER_ALREADY_EXIST);

        }else{

            int resultNum = loginService.registerUser(newUser);

            //注册成功与否
            if(resultNum==SUCCESS){

                result.put(IS_SUCCESS, true);
                result.put(MSG, REGISTER_SUCCESS);
                result.put(USER_LEVEL, level);

            }else{

                result.put(IS_SUCCESS, false);
                result.put(MSG, REGISTER_FAIL);

            }

        }

        return result;

    }



}
