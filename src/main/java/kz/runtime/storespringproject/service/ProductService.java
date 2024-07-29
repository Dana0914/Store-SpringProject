package kz.runtime.storespringproject.service;

import jakarta.transaction.Transactional;
import kz.runtime.storespringproject.entities.Product;
import kz.runtime.storespringproject.repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Product findProductById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Transactional
    public Product createProduct(Product product) {
        Product save = new Product();
        save.setName(product.getName());
        save.setPrice(product.getPrice());
        save.setCategory(product.getCategory());
        return productRepository.save(save);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product productById = findProductById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        return productRepository.save(productById);
    }
}
