package com.example.NIA;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor caloriesRequestInterceptor(@Value("${api.key}") String apikey) {
        return requestTemplate -> requestTemplate.header("authorization", apikey);
    }
}
