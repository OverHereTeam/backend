package com.overhere.backend.dto.response;

import com.overhere.backend.domain.enums.AreaCode;
import com.overhere.backend.domain.enums.UseNonObstacle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "메인 페이지에서 보여줄 지역별, 무장애 정보별 관광지 리스트")
public class ResponseDtoMain {
    @Schema(description = "지역별 관광지 리스트, 조회수와 ㄱ,ㄴ,ㄷ 순으로 정렬")
    private EnumMap<AreaCode, List<ResponseDtoTA.Main>> touristList;
    
    @Schema(description = "무장애 정보별 관광지 리스트, 조회수와 ㄱ,ㄴ,ㄷ 순으로 정렬")
    private EnumMap<UseNonObstacle, List<ResponseDtoTA.Main>> nonObstacleList;
}