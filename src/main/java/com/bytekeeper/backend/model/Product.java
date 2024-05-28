package com.bytekeeper.backend.model;

import com.bytekeeper.backend.model.DTO.Categ;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;


    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Price")
    private Double price;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    @Transient
    private Long categoryId;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Product(Long id, String name, Double price, Integer quantity, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    public Product() {
    }

    public Product(String name, Double price, Integer quantity, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }
    public Product(String name, Double price, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
