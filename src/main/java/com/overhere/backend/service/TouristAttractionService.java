package com.overhere.backend.service;

import com.overhere.backend.dao.TouristAttractionRepository;
import com.overhere.backend.domain.NonObstacleInfo;
import com.overhere.backend.domain.TouristAttraction;
import com.overhere.backend.domain.enums.AreaCode;
import com.overhere.backend.domain.enums.UseNonObstacle;
import com.overhere.backend.dto.response.ResponseDtoMain;
import com.overhere.backend.dto.response.ResponseDtoNonObstacleInfo;
import com.overhere.backend.dto.response.ResponseDtoTA;
import com.overhere.backend.dto.response.urlResponse.ResponseDtoUrl3;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TouristAttractionService {
    private final TouristAttractionRepository touristAttractionRepository;
    
    // 무장애 항목이 존재하는지 확인하는 메서드
    private boolean isNonObstaclePresent(NonObstacleInfo nonObstacleInfo, UseNonObstacle useNonObstacle) {
        return switch (useNonObstacle) {
            case HELP_DOG -> !nonObstacleInfo.getHelpdog().isEmpty();
            case AUDIO_GUIDE -> !nonObstacleInfo.getAudioguide().isEmpty();
            case PUBLIC_TRANSPORT -> !nonObstacleInfo.getPublictransport().isEmpty();
            case PARKING -> !nonObstacleInfo.getParking().isEmpty();
            case RESTROOM -> !nonObstacleInfo.getRestroom().isEmpty();
        };
    }
    
    @Transactional(readOnly = true)
    public ResponseDtoMain findAllForMain() {
        Sort sort = Sort.by(
                Sort.Order.desc("view"),  // view 내림차순
                Sort.Order.asc("title")   // title 오름차순
        );
        
        List<TouristAttraction> touristAttractions = touristAttractionRepository.findAll(sort); // sort 기준으로 정렬해서 반환
        
        if (touristAttractions.isEmpty()) {
            throw new NullPointerException("관광지가 없습니다.");
        }

//        HashMap<AreaCode, List<ResponseDtoTA.Main>> listLocation = new HashMap<>(); // 위치별 관광지 리스트
//        HashMap<UseNonObstacle, List<ResponseDtoTA.Main>> listNonObstacle = new HashMap<>(); // 무장애 정보별 관광지 리스트
        EnumMap<AreaCode, List<ResponseDtoTA.Main>> listLocation = new EnumMap<>(AreaCode.class); // 위치별 관광지 리스트
        EnumMap<UseNonObstacle, List<ResponseDtoTA.Main>> listNonObstacle = new EnumMap<>(UseNonObstacle.class); // 무장애 정보별 관광지 리스트
        
        for (AreaCode areaCode : AreaCode.values()) { // 지역별 관광지 추가
            listLocation.put(areaCode, touristAttractions.stream()
                    .filter(touristAttraction -> touristAttraction.getAreaCode().equals(areaCode.code)) // 서울이면 서울만 추출
                    .limit(8) // 화면에 4개 앞, 뒤로 총 8개
                    .map(ResponseDtoTA.Main::new) // DTO 변환
                    .toList());
        }
        
        for (UseNonObstacle useNonObstacle : UseNonObstacle.values()) {
            listNonObstacle.put(useNonObstacle, touristAttractions.stream()
                    .filter(touristAttraction -> isNonObstaclePresent(touristAttraction.getNonObstacleInfo(), useNonObstacle))
                    .limit(6) // 화면에 총 6개
                    .map(ResponseDtoTA.Main::new)
                    .toList());
        }
        
        ResponseDtoMain response = new ResponseDtoMain();
        response.setTouristList(listLocation);
        response.setNonObstacleList(listNonObstacle);
        return response;
    }
    
    @Transactional(readOnly = true)
    public ResponseDtoTA.Detail findOneDetail(Long touristAttractionId) {
        TouristAttraction findTA = touristAttractionRepository.findById(touristAttractionId)
                .orElseThrow(() -> new RuntimeException("관광지를 찾을 수 없습니다."));
        NonObstacleInfo findNonObstacleInfo = findTA.getNonObstacleInfo();
        
        // TODO - From 사용하기
        ResponseDtoNonObstacleInfo newNonObstacleInfo = ResponseDtoNonObstacleInfo.builder()
                .helpdog(findNonObstacleInfo.getHelpdog())
                .audioguide(findNonObstacleInfo.getAudioguide())
                .videoguide(findNonObstacleInfo.getVideoguide())
                .exitLocation(findNonObstacleInfo.getExitLocation())
                .publictransport(findNonObstacleInfo.getPublictransport())
                .parking(findNonObstacleInfo.getParking())
                .restroom(findNonObstacleInfo.getRestroom())
                .build();
        
        // TODO - From 사용하기
        return ResponseDtoTA.Detail.builder()
                .id(touristAttractionId)
                .title(findTA.getTitle())
                .address1(findTA.getAddress1())
                .thumbnail1(findTA.getThumbnail1())
                .thumbnail2(findTA.getThumbnail2())
                .tel(findTA.getTel())
                .nonObstacleInfo(newNonObstacleInfo)
                .build();
    }
    
    // List 전부 Save
    @Transactional
    public void saveTouristAttractions(List<TouristAttraction> touristAttractionList) {
        touristAttractionRepository.saveAll(touristAttractionList);
    }
    
    // Dto 배열 List로 반환
    public List<TouristAttraction> toTouristAttractionList(ResponseDtoUrl3 responseDtoUrl3) {
        List<TouristAttraction> touristAttractionList = new ArrayList<>();
        
        Stream<ResponseDtoUrl3.Item> stream = responseDtoUrl3.getResponse().getBody().getItems().getItem().stream();
        stream.forEach(item -> {
            TouristAttraction touristAttraction = TouristAttraction.builder()
                    .contentId(item.getContentid())
                    .contentTypeId(item.getContenttypeid())
                    .areaCode(item.getAreacode())
                    .cat1(item.getCat1())
                    .cat2(item.getCat2())
                    .cat3(item.getCat3())
                    .thumbnail1(item.getFirstimage())
                    .thumbnail2(item.getFirstimage2())
                    .tel(item.getTel())
                    .title(item.getTitle())
                    .address1(item.getAddr1())
                    .address2(item.getAddr2())
                    .build();
            touristAttractionList.add(touristAttraction);
        });
        
        return touristAttractionList;
    }
}
