package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Values;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuesRepository extends JpaRepository<Values, Integer> {
}
