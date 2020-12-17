package com.first.memorandum.service.impl;


import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.mapper.UserMapper;
import com.first.memorandum.service.UserService;
import com.first.memorandum.util.MailSender;
import com.first.memorandum.util.Utils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.first.memorandum.enumfolder.ResponseEnum.FAILED;
import static com.first.memorandum.enumfolder.ResponseEnum.SUCCESS;
import static com.first.memorandum.util.Parameter.*;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Value("${authemailaddress}")
    private String authEmailAddress;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


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

    @Override
    public JsonContent sendAuthEmail(String email, String mobileNo) {
        //生成验证token
        String token = Utils.getUuid();
        //保存到redis中
        redisTemplate.opsForValue().set(AUTH_EMAIL_PREFIX+token,mobileNo+REDIS_SEPERATE+email,LOGIN_TIMEOUT_NUM,LOGIN_TIMEOUT_UNIT);
        //拼装访问路径,拼装邮件内容
        String content = "请点击下面链接激活邮箱功能：\n"+authEmailAddress+token;
        //发送邮件
        JsonContent result = null;
        try {
            mailSender.sendEmail(email, content, "自定义备忘录邮箱认证");
            result = new JsonContent(SUCCESS);
        }catch (Exception e){
            LOG.info("邮箱认证邮件发送失败：{}",e.getMessage());
            result = new JsonContent(FAILED);
        }
        return result ;
    }

    @Override
    public Boolean authEmail(String token) {
        if(StringUtils.isBlank(token)){
            return false;
        }
        String redisKey = AUTH_EMAIL_PREFIX+token;
        String keyData = redisTemplate.opsForValue().get(AUTH_EMAIL_PREFIX+token);
        String[] data = keyData.split(REDIS_SEPERATE);
        if(!ArrayUtils.isEmpty(data)){

            int num = userMapper.updateEmailByMobileNo(data[0],data[1]);
            redisTemplate.delete(redisKey);
            if(num>0){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }


}
