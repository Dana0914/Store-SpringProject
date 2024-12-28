package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Product;
import kz.runtime.storespringproject.repos.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;



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
        return productRepository.findProductById(id);
    }
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name);
    }


    public void createProduct(Product product) {
        productRepository.save(product);
    }


    public Product updateProduct(Long id, Product product) {
        Product productById = findProductById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        return productRepository.save(productById);
    }

}
