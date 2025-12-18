package com.inventoryservice.service;


import com.inventoryservice.exception.ResourceNotFoundException;
import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Integer getProductQuantity(Integer id){
        InventoryEntity inventoryEntity = inventoryRepository.findByProductId(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Don't not exist" + id));
        return inventoryEntity.getQuantityAvailable();
    }

    public InventoryEntity addProductQuantity(Integer id,Integer quantity){
        InventoryEntity inventory = inventoryRepository
                .findByProductId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found for productId: " + id
                        )
                );

        int updatedQuantity =
                inventory.getQuantityAvailable() + quantity;

        inventory.setQuantityAvailable(updatedQuantity);
        inventory.setUpdatedAt(LocalDateTime.now());

        return inventoryRepository.save(inventory);
    }

}
