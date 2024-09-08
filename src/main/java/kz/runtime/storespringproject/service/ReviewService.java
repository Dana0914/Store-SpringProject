package kz.runtime.storespringproject.service;


import jakarta.transaction.Transactional;
import kz.runtime.storespringproject.entities.Product;
import kz.runtime.storespringproject.entities.Review;
import kz.runtime.storespringproject.entities.Users;
import kz.runtime.storespringproject.repos.ProductRepository;
import kz.runtime.storespringproject.repos.ReviewRepository;
import kz.runtime.storespringproject.repos.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, UsersRepository usersRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
    }


    public Double getReviewsWithAverageRating() {
        return reviewRepository.getReviewByAverageRating();
    }

    public List<Review> findReviewByProductId(Long id) {
        return reviewRepository.findReviewsByProductId(id);

    }

    public Review findReviewByUserId(Long userId) {
        return reviewRepository.findReviewByUserId(userId);
    }

    @Transactional
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }




}
