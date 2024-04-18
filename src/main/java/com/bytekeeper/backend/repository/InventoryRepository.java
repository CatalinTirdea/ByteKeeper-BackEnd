package com.bytekeeper.backend.repository;

import com.bytekeeper.backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
