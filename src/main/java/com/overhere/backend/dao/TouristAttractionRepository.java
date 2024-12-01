package com.overhere.backend.dao;

import com.overhere.backend.domain.TouristAttraction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Long> {
    List<TouristAttraction> findAll(Sort sort);
}
