package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OptionRepository extends JpaRepository<Options, Integer> {
    @Query(value = "SELECT o.* FROM options o WHERE o.category_id = :id", nativeQuery = true)
    List<Options> findOptionsByCategoryId(@Param("id") Long id);
}
