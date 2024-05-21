package com.bytekeeper.backend.repository;

import com.bytekeeper.backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
