package com.jobapp.reviewms.review.service.impl;


import com.jobapp.reviewms.review.model.Review;
import com.jobapp.reviewms.review.repo.ReviewRepository;
import com.jobapp.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> getReviewsByCompanyId(Long companyId) {

        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {


        if (companyId != null && review != null){

            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public Optional<Review> getReviewByReviewId(Long reviewId) {

            Optional<Review> optionalReview =reviewRepository.findById(reviewId);

            if (optionalReview.isPresent()){
                return reviewRepository.findById(reviewId);
            }

        return Optional.empty();
    }

    @Override
    public boolean updateReviewById(Long reviewId, Review newReview) {
        Optional<Review> optionalReview =reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()){
            Review reviewToUpdate = optionalReview.get();
             reviewToUpdate.setDescription(newReview.getDescription());
             reviewToUpdate.setTitle(newReview.getTitle());
             reviewToUpdate.setRating(newReview.getRating());
             reviewRepository.save(reviewToUpdate);
             return true;

        }
        return false;
    }

    @Override
    public boolean deleteReviewById(Long reviewId) {
        Optional<Review> optionalReview =reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()){
            reviewRepository.deleteById(reviewId);
            return true;

        }
        return false;


    }
}
