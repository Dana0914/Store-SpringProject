package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "SELECT * FROM review r WHERE r.items_id = :id AND r.rating > 3", nativeQuery = true)
    List<Review> findReviewById(@Param ("id") Long id);


}
