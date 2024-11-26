package com.overhere.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestHeaderDto {
    //private String uri;
    private String areaCode;
    private String numOfRows;
    private String pageNo;
    //private String serviceKey;
}
