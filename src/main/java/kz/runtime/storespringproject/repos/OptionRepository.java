package kz.runtime.storespringproject.repos;


import kz.runtime.storespringproject.entities.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options, Integer> {
}
