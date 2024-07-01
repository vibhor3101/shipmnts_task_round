package com.example.service;

import com.example.dao.ReviewRepository;
import com.example.entities.Review;
import com.example.entities.ReviewRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(ReviewRequest reviewRequest) {
        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findAll(pageable).getContent();
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id " + reviewId));
    }

    public Review updateReview(Long reviewId, ReviewRequest reviewRequest) {
        Review existingReview = getReviewById(reviewId);
        existingReview.setRating(reviewRequest.getRating());
        existingReview.setComment(reviewRequest.getComment());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long reviewId) {
        Review existingReview = getReviewById(reviewId);
        reviewRepository.delete(existingReview);
    }
}

