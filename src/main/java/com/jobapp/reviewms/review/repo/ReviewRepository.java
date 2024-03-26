package com.jobapp.reviewms.review.repo;


import com.jobapp.reviewms.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {


    List<Review> findByCompanyId(Long companyId);


    Optional<Review> findByCompanyIdAndId(Long companyId, Long reviewId);
}
