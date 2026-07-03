package com.caleta.lote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient capturaWebClient(){
        return WebClient.builder()
            .baseUrl("https://captura-services.onrender.com")
            .build();
    }

}
