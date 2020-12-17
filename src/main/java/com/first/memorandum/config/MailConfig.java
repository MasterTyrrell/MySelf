package com.first.memorandum.config;

import com.first.memorandum.util.MailSender;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Bean
    @ConfigurationProperties(prefix = "mail")
    public MailSender mailSender(){
        return new MailSender();
    }
}
