package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.Inventory;
import com.bytekeeper.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/inventories")
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/")
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        var res = inventoryService.getInventoryById(id);
        return res.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value="/add", consumes="application/json", produces="application/json")
    public ResponseEntity<?> addInventory(@RequestBody Inventory inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setName(inventoryRequest.getName());
        inventoryService.addInventory(inventory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PutMapping(value="/edit/{id}", consumes="application/json", produces="application/json")
//    public ResponseEntity<?> editInventory(@PathVariable Long id, @RequestBody Inventory inventoryRequest) {
//        Inventory inventory = inventoryService.editInventory(id, inventoryRequest);
//        if (inventory != null) {
//
//        }
//    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
}
