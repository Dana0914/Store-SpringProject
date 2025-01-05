package kz.runtime.storespringproject.service;



import kz.runtime.storespringproject.entities.Review;
import kz.runtime.storespringproject.entities.ReviewStatus;
import kz.runtime.storespringproject.repos.ProductRepository;
import kz.runtime.storespringproject.repos.ReviewRepository;
import kz.runtime.storespringproject.repos.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UsersRepository usersRepository,
                         ProductRepository productRepository) {
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
        return reviewRepository.findReviewByUserId(userId).orElseThrow();
    }

    public void createReview(Review review) {
        Review reviewByUserId = reviewRepository
                .findReviewByUserId(review.getUsers().getId())
                .orElseThrow();

        if (reviewRepository.existsById(reviewByUserId.getId())) {
            throw new RuntimeException("Review already exists");
        }

        if (!review.getReviewStatus().name().equals(ReviewStatus.PUBLISHED.name())) {
            throw new RuntimeException("Review status is not published");
        }
        reviewRepository.save(review);
    }


    public List<Review> findReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }

    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }
}
