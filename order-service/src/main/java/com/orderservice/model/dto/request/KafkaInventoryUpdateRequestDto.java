package com.orderservice.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaInventoryUpdateRequestDto {

    private Integer productId;
    private Integer quantity;

}
