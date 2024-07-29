package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
