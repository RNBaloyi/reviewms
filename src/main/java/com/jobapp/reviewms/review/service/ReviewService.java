package com.jobapp.reviewms.review.service;



import com.jobapp.reviewms.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {


    List<Review> getReviewsByCompanyId(Long companyId);
    boolean addReview(Long companyId, Review review);
    Optional<Review> getReviewByReviewId(Long reviewId);
    boolean updateReviewById(Long reviewId, Review review);
    boolean deleteReviewById(Long reviewId);

}




/*
Reviews
GET /companies/{companyId}/reviews
POST /companies/{companyId}/reviews
GET /companies /{companyId}/reviews/reviewId
PUT /companies/{companyId}/reviews/{reviewId}
DELETE /companies/{companyId}/reviews/{reviewId}
*/