package com.first.memorandum.service;

import com.first.memorandum.dto.JsonContent;


public interface UserService {

    JsonContent resetUser(String mobileNo,String newPassword,String oldPassword);

    JsonContent modifyUserName(String userName,String mobileNo);

}
