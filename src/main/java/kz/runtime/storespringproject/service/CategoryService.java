package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Category;
import kz.runtime.storespringproject.repos.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category findCategoryById(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.get();
    }
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}
