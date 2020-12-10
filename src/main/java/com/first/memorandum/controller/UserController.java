package com.first.memorandum.controller;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/resetPassword")
    @ResponseBody
    public JsonContent resetPassword(String newPassword, String oldPassword, @SessionAttribute("mobileNo")String mobileNo){
        return userService.resetUser(mobileNo,newPassword,oldPassword);

    }

    @RequestMapping(value = "/modifyUserName")
    @ResponseBody
    public JsonContent modifyUserName(String userName,@SessionAttribute("mobileNo")String mobileNo){
        return userService.modifyUserName(userName,mobileNo);
    }
}
