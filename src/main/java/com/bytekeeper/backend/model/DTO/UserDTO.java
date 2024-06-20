package com.bytekeeper.backend.model.DTO;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String mail;
    private String password;
    private List<InventoryDTO> inventories;

    public UserDTO() {
    }

    public UserDTO(String mail, String name, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }
    public UserDTO(String mail, String password) {
        this.mail = mail;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<InventoryDTO> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryDTO> inventories) {
        this.inventories = inventories;
    }
}
