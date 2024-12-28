package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT SUM(r.rating) / COUNT(r.review) FROM review r", nativeQuery = true)
    Double getReviewByAverageRating();

    @Query(value = "SELECT * FROM review r WHERE r.users_id = :id", nativeQuery = true)
    Optional<Review> findReviewByUserId(@Param("id") Long id);

    @Query(value = "SELECT * FROM review r WHERE r.users_id = :id AND r.items_id = :items_id", nativeQuery = true)
    Optional<Review> findReviewByUserIdAndProductId(@Param("id") Long id,
                                                    @Param("items_id") Long itemsId);

    @Query(value = "SELECT * FROM review r WHERE items_id = :id", nativeQuery = true)
    List<Review> findReviewsByProductId(@Param("id") Long id);



}
