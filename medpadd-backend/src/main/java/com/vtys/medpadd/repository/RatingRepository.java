package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
}
