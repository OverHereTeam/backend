package com.overhere.backend.service;

import com.overhere.backend.dto.ApiResponseDto;
import com.overhere.backend.dto.RequestHeaderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class DbInitService {

    private final WebClient webClient;

    @Value("${api.key}")
    private String key;

    public ApiResponseDto fetchTouristAttractionData(URI encode) {
        return webClient.get().uri(encode).retrieve().bodyToMono(ApiResponseDto.class).block();
    }

    public URI buildEncodedUrl(RequestHeaderDto requestHeaderDto) throws UnsupportedEncodingException {
        String encodedKey = URLEncoder.encode(key, "UTF-8");
        return UriComponentsBuilder.fromUriString("https://apis.data.go.kr/B551011/KorWithService1/areaBasedList1")
                .queryParam("serviceKey",encodedKey)
                .queryParam("arrange", "A")
                .queryParam("_type", "json")
                .queryParam("numOfRows", requestHeaderDto.getNumOfRows())
                .queryParam("areaCode",requestHeaderDto.getAreaCode())
                .queryParam("pageNo", requestHeaderDto.getPageNo())
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TEST")
                .build(true)
                .toUri();
    }
}
