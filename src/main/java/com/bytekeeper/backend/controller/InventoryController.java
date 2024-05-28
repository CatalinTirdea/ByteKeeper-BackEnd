package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.Inventory;
import com.bytekeeper.backend.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RequestMapping("/inventories")
@RestController
public class InventoryController {
    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public InventoryController(InventoryService inventoryService, ObjectMapper objectMapper) {
        this.inventoryService = inventoryService;
        this.objectMapper = objectMapper;
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

    @PutMapping(value="/edit/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<?> editInventory(@PathVariable Long id, @RequestBody Inventory inventoryRequest) {
        Inventory inventory = inventoryService.editInventory(id, inventoryRequest);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }

    @GetMapping(value="/search/{name}")
    public List<Inventory> searchInventory(@PathVariable String name) {
        return inventoryService.searchInventoryByName(name);
    }

    @GetMapping(value="/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long id) throws JsonProcessingException {
        Optional<Inventory> inventory = inventoryService.getInventoryById(id);
        String jsonString = objectMapper.writeValueAsString(inventory);
        byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(jsonBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"inventory.json\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
