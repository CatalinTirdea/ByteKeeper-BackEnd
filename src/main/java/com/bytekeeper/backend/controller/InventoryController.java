package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.DTO.Categ;
import com.bytekeeper.backend.model.DTO.InventoryDTO;
import com.bytekeeper.backend.model.DTO.ProductDTO;
import com.bytekeeper.backend.model.Inventory;
import com.bytekeeper.backend.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private InventoryDTO mapInventory(Inventory inv) {
        return new InventoryDTO(
                inv.getId(),
                inv.getName(),
                inv.getProducts().stream()
                        .map(prod -> new ProductDTO(
                                prod.getId(),
                                prod.getName(),
                                prod.getPrice(),
                                prod.getQuantity(),
                                new Categ(
                                        prod.getCategory().getName(),
                                        prod.getCategory().getId()
                                )
                        )).collect(Collectors.toList())
        );
    }

    private List<InventoryDTO> mapInventories(List<Inventory> inventories) {
        return inventories.stream().map(this::mapInventory).toList();
    }

    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        var inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(mapInventories(inventories));
    }

    @GetMapping(name="/id/{id}", produces="application/json")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) {
        var inventory = inventoryService.getInventoryById(id);
        InventoryDTO res;

        if (inventory.isPresent()) {
            res = mapInventory(inventory.get());
            return ResponseEntity.ok(res);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping(value="/add", consumes="application/json", produces="application/json")
    public ResponseEntity<?> addInventory(@RequestBody InventoryDTO inventoryRequest) {
//        var user = userService.getUserByToken(inventoryRequest.getUserId());
        Inventory inventory = new Inventory(inventoryRequest.getName(), inventoryRequest.getVisibility());
        inventoryService.addInventory(inventory);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value="/edit/{id}", consumes="application/json", produces="application/json")
    public ResponseEntity<?> editInventory(@PathVariable Long id, @RequestBody InventoryDTO inventoryRequest) {
        Inventory inventory = inventoryService.editInventory(id, new Inventory(inventoryRequest.getName(), inventoryRequest.getVisibility()));
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
    public ResponseEntity<List<InventoryDTO>> searchInventory(@PathVariable String name) {
        var inventories = inventoryService.searchInventoryByName(name);
        return ResponseEntity.ok(mapInventories(inventories));
    }

    @GetMapping(value="/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long id) throws JsonProcessingException {
        var inventoryFind = inventoryService.getInventoryById(id);
        InventoryDTO inventory;
        if (inventoryFind.isPresent()) {
            inventory = mapInventory(inventoryFind.get());
            String jsonString = objectMapper.writeValueAsString(inventory);
            byte[] jsonBytes = jsonString.getBytes(StandardCharsets.UTF_8);
            ByteArrayResource resource = new ByteArrayResource(jsonBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"inventory.json\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else return ResponseEntity.notFound().build();
    }
}
