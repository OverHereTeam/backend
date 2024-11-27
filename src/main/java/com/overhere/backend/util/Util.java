package com.overhere.backend.util;

import com.overhere.backend.dto.request.RequestDto;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Configuration
public class Util {

    private static final String DOMAIN = "https://apis.data.go.kr/B551011/KorWithService1";

    @Value("${api.key}")
    private String key;

    //3번 OP code URL Build
    public URI buildEncodedUrl3(RequestDto requestDto) throws UnsupportedEncodingException {
        String encodedKey = URLEncoder.encode(key, "UTF-8");
        return UriComponentsBuilder.fromUriString(DOMAIN+"/areaBasedList1")
                .queryParam("serviceKey",encodedKey)
                .queryParam("arrange", "A")
                .queryParam("_type", "json")
                .queryParam("numOfRows", requestDto.getNumOfRows())
                .queryParam("areaCode", requestDto.getAreaCode())
                .queryParam("pageNo", requestDto.getPageNo())
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TEST")
                .build(true)
                .toUri();
    }

    //10번 OP code URL Build
    public URI buildEncodedUrl10(RequestDto requestDto) throws UnsupportedEncodingException{
        String encodedKey = URLEncoder.encode(key, "UTF-8");
        return UriComponentsBuilder.fromUriString(DOMAIN+"/detailWithTour1")
                .queryParam("serviceKey",encodedKey)
                .queryParam("_type", "json")
                .queryParam("contentId", requestDto.getContentId())
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TEST")
                .build(true)
                .toUri();
    }

    //10번 OP code URL Build
    public URI buildEncodedUrl9(RequestDto requestDto) throws UnsupportedEncodingException{
        String encodedKey = URLEncoder.encode(key, "UTF-8");
        return UriComponentsBuilder.fromUriString(DOMAIN+"/detailWithTour1")
                .queryParam("serviceKey",encodedKey)
                .queryParam("arrange", "A")
                .queryParam("_type", "json")
                .queryParam("contendId", requestDto.getAreaCode())
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TEST")
                .build(true)
                .toUri();
    }

    //10번 OP code URL Build
    public URI buildEncodedUrl7(RequestDto requestDto) throws UnsupportedEncodingException{
        String encodedKey = URLEncoder.encode(key, "UTF-8");
        return UriComponentsBuilder.fromUriString(DOMAIN+"/detailWithTour1")
                .queryParam("serviceKey",encodedKey)
                .queryParam("arrange", "A")
                .queryParam("_type", "json")
                .queryParam("contendId", requestDto.getAreaCode())
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "TEST")
                .build(true)
                .toUri();
    }
}
