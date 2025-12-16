package com.productservice.controller;


import com.productservice.model.dto.request.CreateProductRequestDto;
import com.productservice.model.dto.request.UpdateProductRequestDto;
import com.productservice.model.dto.response.CreateProductResponseDto;
import com.productservice.model.dto.response.GetProductResponseDto;
import com.productservice.model.entity.ProductEntity;
import com.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<GetProductResponseDto>> getAllProduct(){
        List<GetProductResponseDto> allProduct = productService.getProduct();
        return new ResponseEntity<>(allProduct,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductResponseDto> getproduct(@PathVariable("id") Integer id){
        GetProductResponseDto productByID = productService.getProductByID(id);
        return new ResponseEntity<>(productByID, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateProductResponseDto> createProduct(
            @Valid @RequestBody CreateProductRequestDto dto) {

        ProductEntity product = productService.createNewProduct(dto);

        CreateProductResponseDto response =
                new CreateProductResponseDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getCreatedAt()
                );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PatchMapping("{id}")
    public ResponseEntity<CreateProductResponseDto> updateProduct(
            @Valid @RequestBody UpdateProductRequestDto dto, @PathVariable("id") Integer id){
        ProductEntity product = productService.updateProduct(dto, id);
        CreateProductResponseDto response =
                new CreateProductResponseDto(
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductPrice(),
                        product.getCreatedAt()
                );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteproduct(@PathVariable("id") Integer id){
         productService.deleteProduct(id);
         return new ResponseEntity<>(true,HttpStatus.OK);
    }

}
