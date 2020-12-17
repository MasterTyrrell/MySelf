package com.first.memorandum.controller;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.enumfolder.ResponseEnum;
import com.first.memorandum.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/modifyUserName")
    @ResponseBody
    public JsonContent modifyUserName(String userName,@SessionAttribute("mobileNo")String mobileNo){
        return userService.modifyUserName(userName,mobileNo);
    }

    @RequestMapping(value = "/sendAuthEmail",produces = "application/json;charset=utf-8")
    @ResponseBody
    public JsonContent authEmail(String email,@SessionAttribute("mobileNo")String mobileNo){
        if(StringUtils.isBlank(email)||StringUtils.isBlank(mobileNo)){
            return new JsonContent(ResponseEnum.NO_EMAIL);
        }
        return userService.sendAuthEmail(email,mobileNo);
    }
}
