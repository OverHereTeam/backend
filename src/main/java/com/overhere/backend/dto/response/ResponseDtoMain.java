package com.overhere.backend.dto.response;

import com.overhere.backend.domain.enums.AreaCode;
import com.overhere.backend.domain.enums.UseNonObstacle;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResponseDtoMain {
    private Map<AreaCode, List<ResponseDtoTA.Main>> touristList;
    private Map<UseNonObstacle, List<ResponseDtoTA.Main>> nonObstacleList;
}
