package com.first.memorandum.Adapter;

import com.first.memorandum.dto.JsonContent;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static com.first.memorandum.enumfolder.ResponseEnum.LOGIN_TIMEOUT;
import static com.first.memorandum.util.Parameter.*;


public class LoginStatusCheckAdapter implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(REQUEST_INFO_HEADER_TOKEN_NAME);
        String mobileNo = getUserLoginMobileNo(token,request);
        if(StringUtils.isBlank(mobileNo)){
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(new JSONObject(new JsonContent(LOGIN_TIMEOUT)).toString());
            return false;
        }

        return true;
    }

    private String getUserLoginMobileNo(String token,HttpServletRequest request){
        if(StringUtils.isBlank(token)){
            return null;
        }
        String mobileNo = redisTemplate.opsForValue().get(LOGIN_PREFIX+token);
        if(StringUtils.isBlank(mobileNo)){
            return null;
        }
        String tokenValue = redisTemplate.opsForValue().get(TOKEN_PREFIX+mobileNo);
        if(StringUtils.isBlank(tokenValue)){
            return null;
        }
        String[] tokenArray = tokenValue.split(REDIS_SEPERATE);
        if(token==null||tokenArray.length<2){
            return null;
        }
        String newestToken = tokenArray[0];
        String userNo = tokenArray[1];
        if(token.equals(newestToken)){
            request.getSession().setAttribute("mobileNo",mobileNo);
            request.getSession().setAttribute("userNo",userNo);
            redisTemplate.expire(LOGIN_PREFIX+token,LOGIN_TIMEOUT_NUM, LOGIN_TIMEOUT_UNIT);
            return mobileNo;
        }
        return null;

    }

}
