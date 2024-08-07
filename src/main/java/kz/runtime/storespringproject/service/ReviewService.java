package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Review;
import kz.runtime.storespringproject.repos.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public List<Review> findReviewById(Long id) {
        return reviewRepository.findReviewById(id);
    }





}
