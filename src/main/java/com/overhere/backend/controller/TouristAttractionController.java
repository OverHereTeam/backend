package com.overhere.backend.controller;

import com.overhere.backend.dto.response.ResponseDtoMain;
import com.overhere.backend.dto.response.ResponseDtoTA;
import com.overhere.backend.service.TouristAttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tourist-attraction")
@Tag(name = "TouristAttraction", description = "관광지 API")
public class TouristAttractionController {
    private final TouristAttractionService touristAttractionService;
    
    @Operation(summary = "메인 페이지에 띄우기 위한 관광지 반환", description = "메인 페이지에서 보여줄 지역별, 무장애 정보별 관광지를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "관광지 목록 반환 성공")
    @GetMapping
    public ResponseEntity<ResponseDtoMain> findAllForMain() {
        return new ResponseEntity<>(touristAttractionService.findAllForMain(), HttpStatus.OK);
    }
    
    @Operation(summary = "관광지 세부 정보 반환", description = "특정 관광지에 대한 세부 정보를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "관광지 세부 정보 반환 성공")
    @GetMapping("/{touristAttractionId}")
    public ResponseEntity<ResponseDtoTA.Detail> findOneDetail(
            @Parameter(description = "검색할 관광지의 id 값", example = "1", required = true)
            @PathVariable Long touristAttractionId) {
        return new ResponseEntity<>(touristAttractionService.findOneDetail(touristAttractionId), HttpStatus.OK);
    }
}
