package indi.simon.kafkalearn.controller;

import indi.simon.kafkalearn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
@Controller
@RequestMapping("/hello")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/kafka")
    @ResponseBody
    public String sayHello(@RequestParam String what){
        return testService.sayHello();
    }



}
