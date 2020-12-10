package com.first.memorandum.config;

import com.first.memorandum.Adapter.LoginStatusCheckAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {


    private List<String> excludePathPatternList = Arrays.asList("/login/*","/error","/**.ico","/test1");
    @Override
    public  void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatusCheckAdapter()).addPathPatterns("/**").excludePathPatterns(excludePathPatternList);
    }

    @Bean
    public HandlerInterceptor loginStatusCheckAdapter(){
        return new LoginStatusCheckAdapter();
    }
}
