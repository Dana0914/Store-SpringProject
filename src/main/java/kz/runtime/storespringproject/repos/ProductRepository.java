package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);

    @Query(value = "select * from items where id = :id", nativeQuery = true)
    Product findProductById(@Param("id") Long id);



}
