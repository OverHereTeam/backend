package com.overhere.backend.repository;

import com.overhere.backend.entity.TouristAttraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristAttractionRepository extends JpaRepository<TouristAttraction,Long> {
}
