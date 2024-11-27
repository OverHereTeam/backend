package com.overhere.backend.controller;

import com.overhere.backend.dto.response.urlResponse.ResponseDtoUrl3;
import com.overhere.backend.dto.request.RequestDto;
import com.overhere.backend.entity.TouristAttraction;
import com.overhere.backend.service.DbInitService;
import com.overhere.backend.service.TouristAttractionService;
import com.overhere.backend.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DbInitController {

    private final DbInitService dbInitService;
    private final TouristAttractionService touristAttractionService;
    private final Util util;

    //numOfRows, pageNo, serviceKey 동적으로 변경해야됨
    //한번에 초기화
    @GetMapping("/{areaCode}/{numOfRows}/{pageNo}")
    public String initTouristAttraction(@PathVariable String areaCode,@PathVariable String numOfRows, @PathVariable String pageNo) throws UnsupportedEncodingException {
        RequestDto requestDto = RequestDto.builder().areaCode(areaCode).numOfRows(numOfRows).pageNo(pageNo).build();
        ResponseDtoUrl3 responseDtoUrl3 = dbInitService.fetchTouristAttractionData(util.buildEncodedUrl3(requestDto));
        List<TouristAttraction> touristAttractionList = touristAttractionService.toTouristAttractionList(responseDtoUrl3);
        touristAttractionService.saveTouristAttractions(touristAttractionList);
        return "ok";
    }


}
