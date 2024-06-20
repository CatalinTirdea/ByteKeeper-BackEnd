package com.bytekeeper.backend.model.DTO;

public class ProductDTO {
    private Long id;
    private String name;
    private Integer quantity;
    private InventoryDTO inventory;
    private Categ category;
    private Long categoryId;
    private Long inventoryId;
    private Double price;

    public ProductDTO(Long id, String name, Double price, Integer quantity, Categ category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    public ProductDTO(Long id, String name, Double price, Integer quantity, Categ category, Long invId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
        this.inventoryId = invId;
    }

    public ProductDTO(String name, Integer quantity, Long categoryId, Long inventoryId, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.inventoryId = inventoryId;
        this.price = price;
    }

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public ProductDTO(Long id, String name, Double price, Integer quantity, Long categoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.price = price;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public InventoryDTO getInventory() {
        return inventory;
    }

    public void setInventory(InventoryDTO inventory) {
        this.inventory = inventory;
    }

    public Categ getCategory() {
        return category;
    }

    public void setCategory(Categ category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
