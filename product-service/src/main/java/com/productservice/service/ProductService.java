package com.productservice.service;

import com.productservice.model.dto.request.CreateProductRequestDto;
import com.productservice.model.entity.ProductEntity;
import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;


    public ProductEntity createNewProduct(CreateProductRequestDto dto) {

        ProductEntity product = ProductEntity.builder()
                .productName(dto.getProductName())
                .productPrice(dto.getProductPrice())
                .createdAt(LocalDateTime.now())
                .build();

        return productRepository.save(product);
    }

}
