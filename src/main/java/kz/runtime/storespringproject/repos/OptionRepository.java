package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Options;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface OptionRepository extends JpaRepository<Options, Integer> {
    @Query(name = "SELECT name FROM options WHERE options.category.id = :id", nativeQuery = true)
    Set<Options> findOptionsByCategoryId(@Param("id") Long id);
}
