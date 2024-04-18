package com.bytekeeper.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Inventory> inventories;
}
