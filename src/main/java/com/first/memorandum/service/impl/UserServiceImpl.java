package com.first.memorandum.service.impl;


import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.enumfolder.ResponseEnum;
import com.first.memorandum.mapper.UserMapper;
import com.first.memorandum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.first.memorandum.enumfolder.ResponseEnum.FAILED;
import static com.first.memorandum.enumfolder.ResponseEnum.SUCCESS;
import static com.first.memorandum.util.Parameter.LOGIN_PREFIX;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public JsonContent resetUser(String mobileNo, String newPassword, String oldPassword){
        Integer num = userMapper.updateUserPassword(mobileNo,DigestUtils.md5DigestAsHex(oldPassword.getBytes()), DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        if(num>0){
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }

    @Override
    public JsonContent modifyUserName(String userName, String mobileNo) {
        int num = userMapper.updateUserName(userName,mobileNo);
        if (num > 0) {
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }


}
