package com.overhere.backend.dto.response;

import com.overhere.backend.domain.TouristAttraction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResponseDtoTA {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Main {
        private int id;
        private String title;
        private String overview;
        private ResponseDtoNonObstacleInfo nonObstacleInfo;
        
        public Main(TouristAttraction touristAttraction) {
            this.title = touristAttraction.getTitle();
//        this.overview = touristAttraction.getOverview();
            this.nonObstacleInfo = new ResponseDtoNonObstacleInfo(touristAttraction.getNonObstacleInfo());
        }
    }
    
    @Data
    @Builder
    public static class Detail {
        private Long id;
        private String title;
        private String address1;
        private String thumbnail1; // 웹
        private String thumbnail2; // 모바일
    
    /* TODO
        private String overview;
        private String homepage;
        private Double mapx; // GPS 경도
        private Double mapy; // GPS 위도
        private int mapLevel;
        private Gallery gallery;
    */
        
        private String tel;
        private ResponseDtoNonObstacleInfo nonObstacleInfo;
    }
}
