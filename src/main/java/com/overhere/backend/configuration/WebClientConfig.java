package com.overhere.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder){

        //numOfRows, pageNo, serviceKey 동적으로 변경해야됨
        return webClientBuilder
                .baseUrl("https://apis.data.go.kr/B551011/KorWithService1")
                .defaultHeader("Accept","application/json")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(Duration.ofSeconds(60))))
                .filter(((request, next) -> {
                    System.out.println("request = " + request);
                    return next.exchange(request)
                            .doOnTerminate(()-> System.out.println("Response"));
                }))
                .build();
    }
}
