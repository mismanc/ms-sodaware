package com.sodaware.web.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${soda.inventory.service-user}") String serviceUser, @Value("${soda.inventory.service-password}") String servicePassword){
        return new BasicAuthRequestInterceptor(serviceUser, servicePassword);
    }

}
