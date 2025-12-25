package com.microservices.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaPaymentCreateRequestDto {

    private Integer orderId;
    private BigDecimal paymentAmount;

}
