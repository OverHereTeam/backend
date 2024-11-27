package com.overhere.backend.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RequestDto {
    private String areaCode;
    private String numOfRows;
    private String pageNo;
    private String contentId;
    private String contentTypeId;
}
