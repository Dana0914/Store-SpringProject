package kz.runtime.storespringproject.repos;

import kz.runtime.storespringproject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
    Category findCategoryById(Long id);
}
