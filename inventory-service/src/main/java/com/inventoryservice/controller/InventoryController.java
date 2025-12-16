package com.inventoryservice.controller;


import com.inventoryservice.model.dto.request.CreateInventoryRequestDto;
import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public void createInventory(CreateInventoryRequestDto dto){

        InventoryEntity newInventory = inventoryService.createInventory(dto);
        log.info("New InventoryCreated: {}", newInventory);

    }

}
