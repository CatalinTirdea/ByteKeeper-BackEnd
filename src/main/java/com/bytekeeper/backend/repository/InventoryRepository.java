package com.bytekeeper.backend.repository;

import com.bytekeeper.backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByNameLikeIgnoreCaseAndVisibilityEquals(String name, String visibility);
}
