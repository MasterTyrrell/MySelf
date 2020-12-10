package com.first.memorandum.service;


import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.User;

public interface LoginService {

    JsonContent sendVerifyCode(String mobileNo, String authCode);

    JsonContent login(User user);

    JsonContent registerUser(User user,String authCode,String verifyCode);

    Boolean checkVerifyCode(String mobileNo,String authCode,String verifyCode);

    JsonContent forgetPassword(String mobileNo,String password,String verifyCode,String authCode);
}
