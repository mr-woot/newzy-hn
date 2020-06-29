package com.example.newzy.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientAdapter {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
