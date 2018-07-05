package com.target.casestudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan(basePackages = {"com.target.casestudy.controller", "com/target/casestudy/repository", "com.target.casestudy.service",
        "com.target.casestudy.domain.product", "com.target.casestudy.client"})
@Order(value = 1)
public class WebAppInit {

    public static void main(String[] args) {
        SpringApplication.run(WebAppInit.class, args);
    }

    @Bean
    public InitialDataSetUp loadInitialData() {
        return new InitialDataSetUp();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder().requestFactory(SimpleClientHttpRequestFactory.class);
    }

}
