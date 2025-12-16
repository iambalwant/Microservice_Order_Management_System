package com.inventoryservice.service;


import com.inventoryservice.controller.InventoryController;
import com.inventoryservice.model.dto.request.CreateInventoryRequestDto;
import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryEntity createInventory(CreateInventoryRequestDto dto){

        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .productId(dto.getProductId())
                .quantityAvailable(dto.getQuantityAvailable())
                .updatedAt(LocalDateTime.now())
                .build();

        return inventoryRepository.save(inventoryEntity);

    }

}
