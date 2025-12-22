package com.orderservice.model.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

    @NotNull(message = "Product id is required")
    @Positive(message = "enter correct Product id")
    private Integer productId;

    @NotNull(message = "quantity is required")
    @PositiveOrZero(message = "Enter correct Product quantity")
    private Integer quantity;
}
