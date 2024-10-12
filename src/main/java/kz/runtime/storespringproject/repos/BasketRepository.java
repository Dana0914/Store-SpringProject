package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select * from basket b where b.items_id = :id", nativeQuery = true)
    List<Basket> findBasketByProductId(@Param("id") Long id);

}
