package com.microservices.model.dto.responce;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class GetPaymentDetailDto {
    private Integer paymentId;
    private Integer orderId;
    private BigDecimal paymentAmount;
    private boolean paymentStatus;
    private LocalDateTime createdAt;
}
