package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query(value = "select * from basket where items_id = :id", nativeQuery = true)
    List<Basket> findBasketByProductId(@Param("id") Long id);


    @Modifying
    @Transactional
    @Query(value = "delete from basket where users_id = :id", nativeQuery = true)
    void clearBasket(@Param("id") Long id);

}
