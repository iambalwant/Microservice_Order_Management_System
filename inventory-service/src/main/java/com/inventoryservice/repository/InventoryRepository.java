package com.inventoryservice.repository;

import com.inventoryservice.model.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
}
