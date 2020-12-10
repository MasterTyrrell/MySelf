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
        String mobileNo = getUserLoginMobileNo(token);
        if(StringUtils.isBlank(mobileNo)){
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(new JSONObject(new JsonContent(LOGIN_TIMEOUT)).toString());
            return false;
        }
        request.getSession().setAttribute("mobileNo",mobileNo);
        return true;
    }

    private String getUserLoginMobileNo(String token){
        if(StringUtils.isBlank(token)){
            return null;
        }
        String mobileNo = redisTemplate.opsForValue().get(LOGIN_PREFIX+token);
        if(StringUtils.isBlank(mobileNo)){
            return null;
        }
        String newestToken = redisTemplate.opsForValue().get(TOKEN_PREFIX+mobileNo);
        if(token.equals(newestToken)){
            redisTemplate.expire(LOGIN_PREFIX+token,LOGIN_TIMEOUT_NUM, LOGIN_TIMEOUT_UNIT);
            return mobileNo;
        }
        return null;

    }

}
