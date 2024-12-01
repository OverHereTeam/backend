package com.overhere.backend.controller;

import com.overhere.backend.dto.response.ResponseDtoMain;
import com.overhere.backend.dto.response.ResponseDtoTA;
import com.overhere.backend.service.TouristAttractionService;
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
public class TouristAttractionController {
    private final TouristAttractionService touristAttractionService;
    
    @GetMapping
    public ResponseEntity<ResponseDtoMain> findAllForMain() {
        return new ResponseEntity<>(touristAttractionService.findAllForMain(), HttpStatus.OK);
    }
    
    @GetMapping("/{touristAttractionId}")
    public ResponseEntity<ResponseDtoTA.Detail> findOneDetail(@PathVariable Long touristAttractionId) {
        return new ResponseEntity<>(touristAttractionService.findOneDetail(touristAttractionId), HttpStatus.OK);
    }
}
