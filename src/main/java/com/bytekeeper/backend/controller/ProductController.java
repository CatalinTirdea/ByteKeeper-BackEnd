package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.Product;
import com.bytekeeper.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        var res = productService.getProductById(id);
        return res.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product productRequest) {
        Product product = new Product();
        product.setQuantity(productRequest.getQuantity());
        product.setCategory(productRequest.getCategory());
        product.setInventory(productRequest.getInventory());
        product.setName(productRequest.getName());
        productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
