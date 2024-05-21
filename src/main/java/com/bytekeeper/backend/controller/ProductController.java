package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.Product;
import com.bytekeeper.backend.model.Category;
import com.bytekeeper.backend.service.ProductService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(value="/products")
@RestController
public class ProductController {

    private final ProductService productService;
//    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value="/add", consumes="application/json", produces="application/json")
    public ResponseEntity<?> addProduct(@RequestBody Product productRequest) {
        Product product = new Product();
        product.setQuantity(productRequest.getQuantity());
        product.setCategory(productRequest.getCategory());
//        product.setInventory(productRequest.getInventory());
        product.setName(productRequest.getName());
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(id, updatedProduct);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
