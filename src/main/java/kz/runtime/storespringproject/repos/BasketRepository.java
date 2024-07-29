package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
}
