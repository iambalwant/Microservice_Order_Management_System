package com.productservice.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestDto {

    @NotBlank(message = "productName must not be blank")
    private String productName;
    @NotNull(message = "productPrice must not be blank")
    @Positive(message = "productPrice must be greater than zero")
    private BigDecimal productPrice;

}
