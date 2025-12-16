package com.productservice.model.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class GetProductResponseDto {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private LocalDateTime createdAt;
}
