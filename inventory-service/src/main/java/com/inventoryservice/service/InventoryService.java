package com.inventoryservice.service;


import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryEntity createInventory(Integer id){

        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .productId(id)
                .quantityAvailable(0)
                .updatedAt(LocalDateTime.now())
                .build();

        return inventoryRepository.save(inventoryEntity);

    }

}
