package com.overhere.backend.dto.response;

import com.overhere.backend.domain.TouristAttraction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseDtoTA {
    
    @Data
    @Schema(description = "메인 페이지에서 보여줄 관광지 정보")
    public static class Main {
        
        @Schema(description = "관광지 식별자", example = "1")
        private Long id;
        
        @Schema(description = "관광지 이름", example = "국립대한민국임시정부기념관")
        private String title;
        
        @Schema(description = "관광지 설명", example = "부안읍에서격포항을향하여변산해수욕장을지나고...")
        private String overview;
        
        @Schema(description = "해당 관광지의 무장애 정보", implementation = ResponseDtoNonObstacleInfo.class)
        private ResponseDtoNonObstacleInfo nonObstacleInfo;
        
        public Main(TouristAttraction touristAttraction) {
            this.id = touristAttraction.getId();
            this.title = touristAttraction.getTitle();
//        this.overview = touristAttraction.getOverview();
            this.nonObstacleInfo = new ResponseDtoNonObstacleInfo(touristAttraction.getNonObstacleInfo());
        }
    }
    
    @Data
    @Builder
    @Schema(description = "관광지 상세 페이지에서 보여줄 관광지 정보")
    public static class Detail {
        
        @Schema(description = "관광지 식별자", example = "1")
        private Long id;
        
        @Schema(description = "관광지 이름", example = "경복궁")
        private String title;
        
        @Schema(description = "관광지 주소", example = "서울특별시중구세종대로11길 35")
        private String address1;
        
        @Schema(description = "관광지 썸네일 - 큰 사이즈", example = "http://tong.visitkorea.or.kr/cms/resource/19/1570619_image3_1.jpg")
        private String thumbnail1; // 웹
        
        @Schema(description = "관광지 썸네일- 작은 사이즈", example = "http://tong.visitkorea.or.kr/cms/resource/19/1570619_image2_1.jpg")
        private String thumbnail2; // 모바일
        
        @Schema(description = "관광지 연락처", example = "02-752-1945")
        private String tel;
        
        /* TODO
        private String overview;
        private String homepage;
        private Double mapx; // GPS 경도
        private Double mapy; // GPS 위도
        private int mapLevel;
        private Gallery gallery;
        */
        
        @Schema(description = "해당 관광지의 무장애 정보", implementation = ResponseDtoNonObstacleInfo.class)
        private ResponseDtoNonObstacleInfo nonObstacleInfo;
    }
}
