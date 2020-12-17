package com.first.memorandum.controller;

import com.first.memorandum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/outer")
@Controller
public class OnterController {
    @Autowired
    private UserService userService;

    @RequestMapping("/authEmail/{authCode}")
    public String authCode(@PathVariable("authCode") String authCode){
        if(userService.authEmail(authCode)){
            return "authSuccess";
        }
        return "authFailed";
    }
}
