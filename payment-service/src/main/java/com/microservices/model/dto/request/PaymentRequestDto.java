package com.microservices.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {
    @NotNull(message = "paymentId is required")
    @PositiveOrZero(message = "enter correct paymentId")
    private Integer paymentId;
    @NotNull(message = "amount is required")
    @PositiveOrZero(message = "enter amount correctly")
    private BigDecimal amount;
}
