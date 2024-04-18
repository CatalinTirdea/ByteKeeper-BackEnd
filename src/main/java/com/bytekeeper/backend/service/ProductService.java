package com.bytekeeper.backend.service;

import com.bytekeeper.backend.model.Product;
import com.bytekeeper.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product editProduct(Long id, Product newProduct) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setCategory(newProduct.getCategory());
                    product.setInventory(newProduct.getInventory());
                    product.setQuantity(newProduct.getQuantity());
                    return repository.save(product);
                }).orElse(null);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
