package com.inventoryservice.controller;


import com.inventoryservice.config.constant.AppConstant;
import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics= AppConstant.PRODUCT_CREATED_TOPIC,groupId = AppConstant.GROUP_ID)
    public void createInventory(Integer id){
        InventoryEntity newInventory = inventoryService.createInventory(id);
        log.info("New InventoryCreated: {}", newInventory);
    }



}
