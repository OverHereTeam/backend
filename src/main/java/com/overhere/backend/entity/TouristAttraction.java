package com.overhere.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristAttraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tourist_attraction_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="non_obstacle_info_id")
    private NonObstacleInfo nonObstacleInfo;

    @OneToMany(mappedBy = "touristAttraction", cascade = CascadeType.ALL)
    private List<TouristAttractionCourse> nonObstacleInfoList = new ArrayList<>();

    private String contentId;

    private String contentTypeId;

    private String areaCode;

    private String cat1;

    private String cat2;

    private String cat3;

    private String thumbnail1;

    private String thumbnail2;

    private String title;

    private String tel;

    private String address1;

    private String address2;

    private Long likeCount;

    private Long view;
}