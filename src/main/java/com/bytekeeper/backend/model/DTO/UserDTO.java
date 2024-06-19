package com.bytekeeper.backend.model.DTO;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String mail;
    private String token;
    private List<InventoryDTO> inventories;

    public UserDTO() {
    }

    public UserDTO(String name, String mail, String token) {
        this.name = name;
        this.mail = mail;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<InventoryDTO> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryDTO> inventories) {
        this.inventories = inventories;
    }
}
