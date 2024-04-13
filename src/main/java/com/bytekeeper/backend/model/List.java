package com.bytekeeper.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "List")
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy="list")
    private ArrayList<Product> products;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
