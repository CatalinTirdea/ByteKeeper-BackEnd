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

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Mail")
    private String mail;

    @Column(name = "Password")
    private String password;

    public User() {
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Inventory> inventories;

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
