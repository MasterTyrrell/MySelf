package com.first.memorandum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
      @RequestMapping("/test1")
      @ResponseBody
      public String test1(){
          String mobileNo = redisTemplate.opsForValue().get("13120656563");
          System.out.println(mobileNo);
          return "12345";
      }

}
