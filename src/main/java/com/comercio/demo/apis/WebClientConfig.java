package com.comercio.demo.apis;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Builder
@Configuration // Indica que es clase de configuracion
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://restcountries.com/v3.1")
                .build();
    }
}
