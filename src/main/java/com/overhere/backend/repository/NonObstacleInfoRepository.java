package com.overhere.backend.repository;

import com.overhere.backend.entity.NonObstacleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonObstacleInfoRepository extends JpaRepository<NonObstacleInfo,Long> {
}
