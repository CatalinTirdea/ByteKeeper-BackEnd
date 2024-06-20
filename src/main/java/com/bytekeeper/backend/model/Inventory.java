package com.bytekeeper.backend.model;

import com.bytekeeper.backend.model.DTO.ProductDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name="Visibility")
    private String visibility;

    @Transient
    private Long userId;

    @JsonManagedReference
    @OneToMany(mappedBy="inventory")
    private List<Product> products;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Inventory(String name) {
        this.name = name;
    }

    public Inventory(Long id, String name, List<Product> products, String visibility, Long userId) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.userId = userId;
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Inventory() {
    }

    public Inventory(String name, String visibility) {
        this.name = name;
        this.visibility = visibility;
    }

    public Inventory(String name, String visibility, Long userId) {
        this.name = name;
        this.visibility = visibility;
        this.userId = userId;
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.getInventories().add(this); // Adăugăm inventarul în lista de inventare a utilizatorului
        }
    }

}
