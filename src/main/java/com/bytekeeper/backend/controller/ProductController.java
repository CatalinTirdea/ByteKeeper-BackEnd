package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.DTO.Categ;
import com.bytekeeper.backend.model.DTO.InventoryDTO;
import com.bytekeeper.backend.model.DTO.ProductDTO;
import com.bytekeeper.backend.model.Product;
import com.bytekeeper.backend.model.Category;
import com.bytekeeper.backend.service.CategoryService;
import com.bytekeeper.backend.service.InventoryService;
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

@RequestMapping(value = "/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, InventoryService inventoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.inventoryService = inventoryService;
    }

    private ProductDTO mapProduct(Product prod) {
        return new ProductDTO(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getQuantity(),
                new Categ(
                        prod.getCategory().getName(),
                        prod.getCategory().getId()
                ),
                prod.getInventory().getId()
        );
    }

    private List<ProductDTO> mapProducts(List<Product> products) {
        return products.stream().map(this::mapProduct).toList();
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        var products = productService.getAllProducts();
        return ResponseEntity.ok(mapProducts(products));
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        var product = productService.getProductById(id);
        ProductDTO res;

        if (product.isPresent()) {
            res = mapProduct(product.get());
            return ResponseEntity.ok(res);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productRequest) {
        var categ = categoryService.getCategoryById(productRequest.getCategoryId()).get();
        var inv = inventoryService.getInventoryById(productRequest.getInventoryId()).get();

        Product product = new Product(
                productRequest.getName(),
                productRequest.getQuantity(),
                productRequest.getPrice(),
                inv,
                categ
        );

        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/edit/{id}", consumes = "application/json")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO updatedProduct) {
        var categ = categoryService.getCategoryById(updatedProduct.getCategoryId()).get();
        var inv = inventoryService.getInventoryById(updatedProduct.getInventoryId()).get();

        Product updated = productService.updateProduct(
                id,
                new Product(
                        updatedProduct.getName(),
                        updatedProduct.getQuantity(),
                        updatedProduct.getPrice(),
                        inv,
                        categ
                )
        );
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
