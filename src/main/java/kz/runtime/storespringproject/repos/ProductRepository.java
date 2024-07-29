package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);


}
