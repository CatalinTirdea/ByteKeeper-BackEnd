package com.bytekeeper.backend.model;

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

    @OneToMany(mappedBy="inventory")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.getInventories().add(this); // Adăugăm inventarul în lista de inventare a utilizatorului
        }
    }

}
