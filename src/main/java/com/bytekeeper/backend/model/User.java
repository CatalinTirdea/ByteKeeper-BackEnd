package com.bytekeeper.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="\"User\"")  // User trebuie intre "" pentru ca e cuvant rezervat in Postgres
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Mail")
    private String mail;

    @Column(name="Password")
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Inventory> inventories;

    public User(String mail, String name, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }


    public User() {
    }
    public void deleteInventory(Inventory inventory) {
        inventories.remove(inventory);  // Stergem invetarul
        inventory.setUser(null); // Stergem userul de la inventarul respectiv
    }

    public void deleteProductFromInventory(Inventory inventory, Product product) {
        if (inventory != null && inventory.getProducts() != null) {
            inventory.getProducts().remove(product);                //    Verifica daca inventarul exista si daca acesta nu este gol apoi sterge produsul respectiv.
        }
    }


}
