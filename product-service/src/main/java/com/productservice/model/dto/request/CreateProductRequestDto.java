package com.productservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "productName must not be blank")
    private String productName;

    @NotNull(message = "productPrice must not be null")
    @Positive(message = "productPrice must be greater than zero")
    private BigDecimal productPrice;
}
