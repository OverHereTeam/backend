package com.overhere.backend.service;

import com.overhere.backend.dto.response.urlResponse.ResponseDtoUrl3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class DbInitService {

    private final WebClient webClient;

    public ResponseDtoUrl3 fetchTouristAttractionData(URI encode) {
        return webClient.get().uri(encode).retrieve().bodyToMono(ResponseDtoUrl3.class).block();
    }
}
