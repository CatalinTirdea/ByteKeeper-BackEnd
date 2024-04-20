package com.bytekeeper.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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


    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

}
