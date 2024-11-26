package com.overhere.backend.service;

import com.overhere.backend.dto.ApiResponseDto;
import com.overhere.backend.dto.RequestHeaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class DbInitService {

    private final WebClient webClient;

    @Value("${api.key}")
    private String key;

    private String uri = "/areaBasedList1";

    public Mono<ApiResponseDto> fetchTouristAttractionData(RequestHeaderDto requestHeaderDto) {
        System.out.println("key = " + key);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(uri)
                        .queryParam("numOfRows", requestHeaderDto.getNumOfRows())
                        .queryParam("pageNo", requestHeaderDto.getPageNo())
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "TEST")
                        .queryParam("serviceKey", key)
                        .queryParam("arrange", "A")
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .bodyToMono(ApiResponseDto.class);
    }
}
