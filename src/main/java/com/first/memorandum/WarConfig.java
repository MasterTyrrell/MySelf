package com.first.memorandum;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarConfig extends SpringBootServletInitializer {

    //注意,这里的DemoApplication是启动类
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MemorandumApplication.class);
    }
}
