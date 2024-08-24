package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT SUM(r.rating) / COUNT(r.review) FROM review r", nativeQuery = true)
    Double getReviewByAverageRating();


    @Query(value = "SELECT * FROM review r WHERE items_id = :id", nativeQuery = true)
    List<Review> findReviewsByProductId(@Param("id") Long id);

}
