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
        var res = productService.getProductById(id);
        return res.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value="/getCategories", produces="application/json")
    public ResponseEntity<ArrayList<Categ>> getCategories() {
        ArrayList<Categ> jsonArray = new ArrayList<>();
        /* pentru cand e facut service pentru Category
        List<Category> categories = categoryService.getAllCategories();
        for (var category : categories) {
            categories.add(new Categ(category.getName(), category.getId()));
        }*/

        jsonArray.add(new Categ("Food", 1L));
        jsonArray.add(new Categ("Electronics", 2L));
        jsonArray.add(new Categ("Sports", 3L));
        return ResponseEntity.ok(jsonArray);
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
}
