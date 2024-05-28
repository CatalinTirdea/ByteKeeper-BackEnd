package com.bytekeeper.backend.service;

import com.bytekeeper.backend.model.Inventory;
import com.bytekeeper.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository repository;

    @Autowired
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<Inventory> getAllInventories() {
        return repository.findAll();
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return repository.findById(id);
    }

    public Inventory addInventory(Inventory inventory) {
        return repository.save(inventory);
    }

    public Inventory editInventory(Long id, Inventory newInventory) {
        return repository.findById(id)
                .map(inventory -> {
                    inventory.setName(newInventory.getName());
                    return repository.save(inventory);
                }).orElse(null);
    }

    public void deleteInventory(Long id) {
        repository.deleteById(id);
    }

    public List<Inventory> searchInventoryByName(String name) {
        return repository.findByNameLikeIgnoreCaseAndVisibilityEquals(name, "public");
    }
}
