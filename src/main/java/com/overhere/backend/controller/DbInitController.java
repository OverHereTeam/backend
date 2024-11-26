package com.overhere.backend.controller;

import com.overhere.backend.dto.ApiResponseDto;
import com.overhere.backend.dto.RequestHeaderDto;
import com.overhere.backend.service.DbInitService;
import com.overhere.backend.service.TouristAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DbInitController {

    private final DbInitService dbInitService;
    private final TouristAttractionService touristAttractionService;

    //numOfRows, pageNo, serviceKey 동적으로 변경해야됨
    @GetMapping("/{areaCode}/{numOfRows}/{pageNo}")
    public String initTouristAttraction(@PathVariable String areaCode,@PathVariable String numOfRows, @PathVariable String pageNo) throws UnsupportedEncodingException {
        RequestHeaderDto requestHeaderDto = RequestHeaderDto.builder().areaCode(areaCode).numOfRows(numOfRows).pageNo(pageNo).build();
        Mono<ApiResponseDto> apiResponseDtoMono = dbInitService.fetchTouristAttractionData(requestHeaderDto);
        touristAttractionService.saveTouristAttractions(apiResponseDtoMono);
        return "ok";
    }
}
