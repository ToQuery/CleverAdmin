package com.toquery.cleverweb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author toquery
 * @version 1
 */
@Controller
@RequestMapping("/test")
public class TestController {

     @Autowired
    private RedisTemplate redisTemplate;


     @ResponseBody
     @RequestMapping("/redis/{content}")
     public String getRedis(@PathVariable String content){
         redisTemplate.opsForValue().set("test",content);
         return (String) redisTemplate.opsForValue().get("test");
     }


}
