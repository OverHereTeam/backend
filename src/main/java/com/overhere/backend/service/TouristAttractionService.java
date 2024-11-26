package com.overhere.backend.service;

import com.overhere.backend.dto.ApiResponseDto;
import com.overhere.backend.entity.TouristAttraction;
import com.overhere.backend.repository.TouristAttractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TouristAttractionService {
    private final TouristAttractionRepository touristAttractionRepository;

    @Transactional
    public void saveTouristAttractions(Mono<ApiResponseDto> apiResponseDto){

        List<TouristAttraction> touristAttractionList = toTouristAttractionList(apiResponseDto);
        touristAttractionRepository.saveAll(touristAttractionList);
    }




    public List<TouristAttraction> toTouristAttractionList(Mono<ApiResponseDto> apiResponseDto) {
        List<TouristAttraction> touristAttractionList = new ArrayList<>();

        apiResponseDto.subscribe(dto->{
            Stream<ApiResponseDto.Item> stream = dto.getResponse().getBody().getItems().getItem().stream();
            stream.forEach(item->{
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
        });
        return touristAttractionList;
    }


//    @Transactional
//    public void saveTouristAttractions(Mono<ApiResponseDto> apiResponseDto){
//        apiResponseDto
//                .map(this::toTouristAttractionList)
//                .subscribe(touristAttractionList ->
//                        touristAttractionRepository.saveAll(touristAttractionList)
//                );
//    }
//
//    private List<TouristAttraction> toTouristAttractionList(ApiResponseDto dto) {
//        return dto.getResponse().getBody().getItems().getItem().stream()
//                .map(item -> TouristAttraction.builder()
//                        .contentId(item.getContentid())
//                        .contentTypeId(item.getContenttypeid())
//                        .areaCode(item.getAreacode())
//                        .cat1(item.getCat1())
//                        .cat2(item.getCat2())
//                        .cat3(item.getCat3())
//                        .thumbnail1(item.getFirstimage())
//                        .thumbnail2(item.getFirstimage2())
//                        .tel(item.getTel())
//                        .title(item.getTitle())
//                        .address1(item.getAddr1())
//                        .address2(item.getAddr2())
//                        .build())
//                .collect(Collectors.toList());
//    }

}
