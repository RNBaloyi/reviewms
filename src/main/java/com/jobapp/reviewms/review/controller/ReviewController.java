package com.jobapp.reviewms.review.controller;



import com.jobapp.reviewms.review.dto.ReviewMessageProducer;
import com.jobapp.reviewms.review.model.Review;
import com.jobapp.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMessageProducer reviewMessageProducer;

    @GetMapping
    ResponseEntity<List<Review>> getReviewsByCompanyId(@RequestParam Long companyId){
         List<Review> reviews = reviewService.getReviewsByCompanyId(companyId);
         return new ResponseEntity<>(reviews,HttpStatus.OK);
     }


     @PostMapping
    ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){

        boolean isReviewAdded = reviewService.addReview(companyId,review);
        if (isReviewAdded){
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("review successfully saved",HttpStatus.OK);

        }else {
            return new ResponseEntity<>("review not saved",HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/{reviewId}")

   ResponseEntity<Optional<Review>> getReviewByReviewId(@PathVariable Long reviewId){
        Optional<Review> review = reviewService.getReviewByReviewId(reviewId);

        if (review.isPresent()){
            return new ResponseEntity<>(review,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{reviewId}")
    ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,@RequestBody Review newReview) {
        boolean isReviewUpdated = reviewService.updateReviewById(reviewId,newReview);

        if (isReviewUpdated){
            return new ResponseEntity<>("review updated successfully ",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("review not updated",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReviewById(reviewId);

        if (isReviewDeleted){
            return new ResponseEntity<>("review deleted successfully ",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("review not found",HttpStatus.NOT_FOUND);
        }
    }




}

//    REVIEWS MS
//
//
//        GET /reviews?companyId={companyId}
//        POST /reviews?companyId={companyId}
//        GET /reviews/{reviewId}
//        PUT /reviews/{reviewId}
//        DELETE /reviews/{reviewId}




/*Reviews
   List<Review> getAllReviews();
    void createReview(Review review);
    Optional<Review> getReviewById(Long id);
    boolean updateReviewById(Long id,Review review);
    boolean deleteReviewById(Long id);



GET /companies/{companyId}/reviews
POST /companies/{companyId}/reviews
GET /companies /{companyId}/reviews/reviewId
PUT /companies/{companyId}/reviews/{reviewId}
DELETE /companies/{companyId}/reviews/{reviewId}
*/