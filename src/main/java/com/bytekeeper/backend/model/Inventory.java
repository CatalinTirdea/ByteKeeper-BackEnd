package com.bytekeeper.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy="inventory")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
