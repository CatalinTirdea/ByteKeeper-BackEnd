package com.bytekeeper.backend.model.DTO;

import com.bytekeeper.backend.model.User;

import java.util.List;

public class InventoryDTO {
    private Long id;
    public String name;
    public String visibility;
    private List<ProductDTO> products;
    private UserDTO user;

    public InventoryDTO(Long id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public InventoryDTO(String name, String visibility) {
        this.name = name;
        this.visibility = visibility;
    }

    public InventoryDTO(Long id, String name, String visibility) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
    }

    public InventoryDTO() {
    }

    public InventoryDTO(Long id, String name, String visibility, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.products = products;
    }

    public InventoryDTO(Long id, String name, String visibility, List<ProductDTO> products, UserDTO user) {
        this.id = id;
        this.name = name;
        this.visibility = visibility;
        this.products = products;
        this.user = user;
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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
