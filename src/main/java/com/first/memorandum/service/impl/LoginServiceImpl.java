package com.first.memorandum.service.impl;

import com.first.memorandum.dto.JsonContent;
import com.first.memorandum.entity.User;
import com.first.memorandum.enumfolder.SmsResponseEnum;
import com.first.memorandum.enumfolder.UserStatusEnum;
import com.first.memorandum.enumfolder.UserTypeEnum;
import com.first.memorandum.mapper.UserMapper;
import com.first.memorandum.service.LoginService;
import com.first.memorandum.util.Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;


import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.first.memorandum.enumfolder.ResponseEnum.*;
import static com.first.memorandum.util.Parameter.*;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${sms.uid}")
    private String uid;
    @Value("${sms.key}")
    private String key;
    @Value("${sms.ip}")
    private String ip;
    @Value("${defaultImg}")
    private String defaultImg;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserMapper userMapper;

    private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Override
    public JsonContent sendVerifyCode(String mobileNo, String authCode) {
        //检查redis次数 没有超过则递增一次
        if(checkRedisThanFlag(VERIFY_CODE_COUNT,60)){
            return new JsonContent(VERIFYCODE_SEND_THAN);
        }
        Random random = new Random();
        Integer num = random.nextInt(1000000);
        String verifyCode = StringUtils.leftPad(num.toString(),6,"0");
        String msg = "验证码: \n " + verifyCode;
        Map<String,String> requestMap = new HashMap<>(4);
        requestMap.put("uid",uid);
        requestMap.put("key",key);
        requestMap.put("smsMob",mobileNo);
        requestMap.put("smsText",msg);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(ip,String.class,requestMap);
        String result = responseEntity.getBody();
        String desc = SmsResponseEnum.getDscByCode(result);
        JsonContent jsonContent =  new JsonContent(result,desc,null);
        if(!"1".equals(result)){
            LOG.warn("短信发送失败：code:{},dsc:{}",result, desc);
            return jsonContent;
        }
        redisTemplate.opsForValue().set(mobileNo + REDIS_SEPERATE + authCode,verifyCode,2,TimeUnit.MINUTES);
        return  jsonContent;
    }

    @Override
    public Boolean checkVerifyCode(String mobileNo,String authCode,String verifyCode){
        if(StringUtils.isBlank(mobileNo)||StringUtils.isBlank(authCode)||StringUtils.isBlank(verifyCode)){
            return false;
        }
        String redisKey = mobileNo + REDIS_SEPERATE + authCode;
        String saveVerifyCode = redisTemplate.opsForValue().get(redisKey);
        if(verifyCode.equals(saveVerifyCode)){
            redisTemplate.delete(redisKey);
            return true;
        }
        return false;
    }

    @Override
    public JsonContent forgetPassword(String mobileNo, String password, String verifyCode, String authCode) {
        //该手机号是否注册
        User havingUser = userMapper.getUserByMobileNo(mobileNo,false,UserStatusEnum.VALID.getCode(),null);
        if(havingUser==null){
            return new JsonContent(NO_USER);
        }
        if(StringUtils.isBlank(mobileNo)||StringUtils.isBlank(password)){
            return new JsonContent(NO_MOBILENO_PASSWORD);
        }
        if(!checkVerifyCode(mobileNo,authCode,verifyCode)){
            return new JsonContent(VERIFYCODE_ERROR);
        }
        Integer result = userMapper.updateUserPassword(mobileNo,null,DigestUtils.md5DigestAsHex(password.getBytes()));
        if(result>0){
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(NO_USER);
    }

    @Override
    public JsonContent login(User user) {
        if(user==null||StringUtils.isBlank(user.getMobileNo())||StringUtils.isBlank(user.getPassword())){
            return new JsonContent(NO_MOBILENO_PASSWORD);
        }
        User getUser = userMapper.getUserByMobileNo(user.getMobileNo(),null,null,null);
        if(getUser == null){
            return new JsonContent(NO_USER);
        }
        if(getUser.getDelFlag()||UserStatusEnum.INVALID.getCode().equals(getUser.getStatus())){
            return new JsonContent(ERROR_USER);
        }
        if(!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(getUser.getPassword())){
            return new JsonContent(ERROR_PASSWORD);
        }
        getUser.setToken(Utils.getUuid());
        //删除多余的token记录
       String oldToken =  redisTemplate.opsForValue().get(TOKEN_PREFIX + getUser.getMobileNo());
        redisTemplate.delete(LOGIN_PREFIX + oldToken);
       redisTemplate.opsForValue().set(LOGIN_PREFIX + getUser.getToken(),getUser.getMobileNo(),LOGIN_TIMEOUT_NUM,LOGIN_TIMEOUT_UNIT);
        redisTemplate.opsForValue().set( TOKEN_PREFIX + getUser.getMobileNo(),getUser.getToken()+ REDIS_SEPERATE + getUser.getUserNo(),LOGIN_TIMEOUT_NUM,LOGIN_TIMEOUT_UNIT);
        user.setPassword(null);
        return new JsonContent(SUCCESS,getUser);
    }

    @Override
    public JsonContent registerUser(User user,String authCode,String verifyCode) {
        //该手机号是否注册
        User havingUser = userMapper.getUserByMobileNo(user.getMobileNo(),null,null,null);
        if(havingUser!=null){
            return new JsonContent(HAVING_USER);
        }
        if(user==null||StringUtils.isBlank(user.getMobileNo())||StringUtils.isBlank(user.getPassword())){
            return new JsonContent(NO_MOBILENO_PASSWORD);
        }
        if(!checkVerifyCode(user.getMobileNo(),authCode,verifyCode)){
            return new JsonContent(VERIFYCODE_ERROR);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setDelFlag(false);
        user.setStatus(UserStatusEnum.VALID.getCode());
        user.setUserName(user.getMobileNo());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setUserType(UserTypeEnum.USER.getCode());
        user.setImageUrl(defaultImg);
        //从redis中获取用户号
        String userNo = redisTemplate.opsForValue().get("userNo");
        if(userNo==null){
            userNo = "0";
        }
        user.setUserNo(StringUtils.leftPad(userNo,19,"0"));
        Boolean flag = true;
        Integer num = 0;
        while(flag) {
            try {
                num = userMapper.insertUser(user);
                flag = false;
            }catch (DuplicateKeyException e){
                String newUserNo = redisTemplate.opsForValue().get("userNo");
                if(newUserNo!=null&&(userNo.equals(newUserNo))){
                    redisTemplate.opsForValue().increment("userNo");
                    userNo = String.valueOf(Integer.valueOf(userNo)+1);
                    user.setUserNo(StringUtils.leftPad(userNo,19,"0"));
                }else if(newUserNo==null||Integer.valueOf(newUserNo).compareTo(Integer.valueOf(userNo))<0){
                    userNo = String.valueOf(Integer.valueOf(userNo)+1);
                    user.setUserNo(StringUtils.leftPad(userNo,19,"0"));
                    redisTemplate.opsForValue().set("userNo",userNo);
                }else{
                    user.setUserNo(StringUtils.leftPad(newUserNo,19,"0"));
                }
            }
        }
        if(num>0){
            redisTemplate.opsForValue().increment("userNo");
            return new JsonContent(SUCCESS);
        }
        return new JsonContent(FAILED);
    }

    private Boolean checkRedisThanFlag(String key,Integer max){
        if(key==null||max==null){
            return true;
        }
        String numStr = redisTemplate.opsForValue().get(key);
        if(numStr!=null){
            Integer num = Integer.valueOf(numStr);
            if(num>=max){
                return true;
            }
            redisTemplate.opsForValue().increment(key);
        }else{
            redisTemplate.opsForValue().set(key,"1",1,TimeUnit.DAYS);
        }
        return false;
    }
}
