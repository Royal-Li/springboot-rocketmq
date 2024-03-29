package com.person.test.demo_producer.controller;

import com.person.test.demo_producer.pojo.User;
import com.person.test.demo_producer.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Controller
public class ProducerController {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping("/producerTest1")
    public String producerTest1 (String param1) {
        Random random = new Random();
        int version = random.nextInt(10000);
        rocketMQTemplate.convertAndSend("topic201912021032","我是话题topic201912021032-"+version);
        return version+"";
    }

    @ResponseBody
    @RequestMapping("/queryUser1")
    public List<User> queryUser1(){
        return userService.queryAll();
    }

    @ResponseBody
    @RequestMapping("/queryUser2")
    public List<User> queryUser2() {
        return userService.queryAllByDBTwo();
    }

    @ResponseBody
    @RequestMapping("/updateUserOne")
    public String updateUserOne(){
        Random random = new Random();
        int i = random.nextInt(10000);
        User user = new User();
        user.setId(1);
        user.setName("修改后的张三"+i);
        return userService.updateUserOne(user);
    }

    @ResponseBody
    @RequestMapping("/updateUserTwo")
    public String updateUserTwo(){
        Random random = new Random();
        int i = random.nextInt(10000);
        User user = new User();
        user.setId(1);
        user.setName("修改后的王二"+i);
        return userService.updateUserTwo(user);
    }

}
