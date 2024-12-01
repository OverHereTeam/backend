package com.overhere.backend.dto.response;

import com.overhere.backend.domain.NonObstacleInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor  // 모든 필드를 받는 생성자 추가
@NoArgsConstructor   // 기본 생성자 추가
public class ResponseDtoNonObstacleInfo {
    private String helpdog; // 안내견 동반 가능 여부
    private String audioguide; // 오디오 가이드
    private String videoguide; // 비디오 가이드
    private String exitLocation; // 출입 통로
    private String publictransport; // 접근 경로
    private String parking; // 장애인 주차구역
    private String restroom; // 장애인 화장실
    
    public ResponseDtoNonObstacleInfo(NonObstacleInfo nonObstacleInfo) {
        this.helpdog = nonObstacleInfo.getHelpdog();
        this.audioguide = nonObstacleInfo.getAudioguide();
        this.videoguide = nonObstacleInfo.getVideoguide();
        this.exitLocation = nonObstacleInfo.getExitLocation();
        this.publictransport = nonObstacleInfo.getPublictransport();
        this.parking = nonObstacleInfo.getParking();
        this.restroom = nonObstacleInfo.getRestroom();
    }
}
