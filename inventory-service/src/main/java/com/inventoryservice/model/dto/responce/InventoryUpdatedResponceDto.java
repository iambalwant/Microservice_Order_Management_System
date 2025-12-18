package com.inventoryservice.model.dto.responce;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class InventoryUpdatedResponceDto {

    private Integer id;
    private Integer quantity;
    private Integer productId;
    private LocalDateTime updatedAt;

}
