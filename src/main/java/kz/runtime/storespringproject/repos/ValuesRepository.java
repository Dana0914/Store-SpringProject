package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Values;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ValuesRepository extends JpaRepository<Values, Integer> {
    @Query(value = "SELECT v.* FROM values v WHERE v.item_id = :id", nativeQuery = true)
    List<Values> findValuesByOptionId(@Param("id") Long id);
}
