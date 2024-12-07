package com.overhere.backend.dto.response;

import com.overhere.backend.domain.NonObstacleInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "무장애 정보 클래스") // 클래스 설명
public class ResponseDtoNonObstacleInfo {
    
    @Schema(description = "안내견 동반 가능 여부", example = "동반가능_시각장애인 편의시설")
    private String helpdog; // 안내견 동반 가능 여부
    
    @Schema(description = "오디오 가이드", example = "음성안내 가이드 있음(티켓박스에서 음성안내기기와 PDA 대여가능)")
    private String audioguide; // 오디오 가이드
    
    @Schema(description = "비디오 가이드", example = "한국수어사전 해설동영상 서비스 운영")
    private String videoguide; // 비디오 가이드
    
    @Schema(description = "출입 통로", example = "지하주차장 입구는 단차가 없어 휠체어 접근 가능함")
    private String exitLocation; // 출입 통로
    
    @Schema(description = "접근 경로", example = "주출입구는 계단이 있어 지하주차장 입구를 통해 접근 가능함")
    private String publictransport; // 접근 경로
    
    @Schema(description = "장애인 주차구역", example = "장애인 주차장 있음(지하주차장)_무장애 편의시설")
    private String parking; // 장애인 주차구역
    
    @Schema(description = "장애인 화장실", example = "장애인 화장실 있음")
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
