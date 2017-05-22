package com.toquery.cleverweb.web.controller.test;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    @RequestMapping("/login")
    public void UserLogin(){
        return;
    }
}
