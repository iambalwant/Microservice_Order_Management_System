package com.productservice.controller;


import com.productservice.model.dto.request.CreateProductRequestDto;
import com.productservice.model.dto.response.CreateProductReponseDto;
import com.productservice.model.entity.ProductEntity;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public void getAllProduct(){

    }

    @GetMapping("/{id}")
    public void getproduct(){
    }

    @PostMapping
    public ResponseEntity<CreateProductReponseDto> createProduct(
            @Valid @RequestBody CreateProductRequestDto dto) {

        ProductEntity product = productService.createNewProduct(dto);

        CreateProductReponseDto response =
                new CreateProductReponseDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getCreatedAt()
                );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PatchMapping
    public void updateProduct(){

    }

    @DeleteMapping
    public void deleteproduct(){

    }
}
