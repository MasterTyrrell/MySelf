package com.first.memorandum.controller;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.User;
import com.first.memorandum.service.LoginService;
import com.first.memorandum.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.first.memorandum.enumfolder.ResponseEnum.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    /**
     * 注册发送验证码
     */
    @RequestMapping("/sendVerificationCode")
    @ResponseBody
    public JsonContent sendVerificationCode(String mobilePhone,String authCode){
        if(StringUtils.isBlank(authCode)||StringUtils.isBlank(mobilePhone)){
            return new JsonContent(NO_AUTH_CODE);
        }
        return loginService.sendVerifyCode(mobilePhone,authCode);
    }

    @RequestMapping("/loginUser")
    @ResponseBody
    public JsonContent loginUser(User user){
        return loginService.login(user);
    }

    @RequestMapping("/registerUser")
    @ResponseBody
    public JsonContent registerUser(User user,String authCode,String verifyCode){
        if(user==null||StringUtils.isBlank(user.getMobileNo())|| StringUtils.isBlank(authCode)|| StringUtils.isBlank(verifyCode)){
             return new JsonContent(NO_MOBILENO_AUTHCODE_VERIFYCODE);
        }
        return loginService.registerUser(user,authCode,verifyCode);
    }

    @RequestMapping("/forgetPassword")
    @ResponseBody
    public JsonContent resetPassword(String mobileNo,String newPassword,String verifyCode,String authCode){
        return loginService.forgetPassword(mobileNo,newPassword,verifyCode,authCode);
    }

}
