package kz.runtime.storespringproject.repos;

import jakarta.transaction.Transactional;
import kz.runtime.storespringproject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT SUM(r.rating) / COUNT(r.review) FROM review r", nativeQuery = true)
    Double getReviewByAverageRating();

    @Query(value = "SELECT review FROM review r WHERE users_id = :user_id", nativeQuery = true)
    Review findReviewByUserId(@Param("user_id") Long userId);

    @Query(value = "SELECT * FROM review r WHERE items_id = :id", nativeQuery = true)
    List<Review> findReviewsByProductId(@Param("id") Long id);



}
