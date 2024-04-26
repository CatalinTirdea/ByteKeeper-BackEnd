package com.bytekeeper.backend.model;

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


    @ManyToOne
    @JoinColumn(name="inventory_id")
    private Inventory inventory;

    @Transient
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


    public void displayDetails() {
        System.out.println("Detalii despre produs:");
        System.out.println("ID: " + this.id);
        System.out.println("Nume: " + this.name);
        System.out.println("Cantitate: " + this.quantity);
    }


    public void changeProduct(String newName, Integer newQuantity) {
        if (newName != null) {
            this.name = newName;
        }

        if (newQuantity != null) {
            this.quantity = newQuantity;
        }

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
}
