package com.inventoryservice.controller;


import com.inventoryservice.config.constant.AppConstant;
import com.inventoryservice.model.dto.responce.InventoryUpdatedResponceDto;
import com.inventoryservice.model.entity.InventoryEntity;
import com.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<Integer> ProductQuantity(@PathVariable Integer id){
        Integer productQuantity = inventoryService.getProductQuantity(id);
        return new ResponseEntity<>(productQuantity, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<InventoryUpdatedResponceDto> AddProductQuantity(
                                   @Valid
                                   @PathVariable Integer id,
                                   @RequestParam Integer quantity
                                   ){
        InventoryEntity updatedQuantity = inventoryService.addProductQuantity(id, quantity);
        InventoryUpdatedResponceDto responce = InventoryUpdatedResponceDto.builder()
                .productId(updatedQuantity.getProductId())
                .quantity(updatedQuantity.getQuantityAvailable())
                .id(updatedQuantity.getInventoryId())
                .updatedAt(updatedQuantity.getUpdatedAt())
                .build();
        return new ResponseEntity<>(responce,HttpStatus.OK);
    }

    public void orderedProductQuantityUpdated(){

    }

}
