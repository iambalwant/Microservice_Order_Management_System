package com.productservice.service;

import com.productservice.config.constant.AppContants;
import com.productservice.exception.GlobalExceptionHandler;
import com.productservice.exception.ResourceNotFoundException;
import com.productservice.model.dto.request.CreateProductRequestDto;
import com.productservice.model.dto.request.UpdateProductRequestDto;
import com.productservice.model.dto.response.GetProductResponseDto;
import com.productservice.model.entity.ProductEntity;
import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final KafkaTemplate<String,Integer> kafkaTemplate;
    private final ProductRepository productRepository;


    public List<GetProductResponseDto> getProduct(){
        List<ProductEntity> allProducts = productRepository.findAll();
        return allProducts.stream()
                .map(product -> GetProductResponseDto.builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .productPrice(product.getProductPrice())
                        .createdAt(product.getCreatedAt())
                        .build()
                )
                .toList();
    }

    public GetProductResponseDto getProductByID(Integer id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + id)
                );

        return GetProductResponseDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .createdAt(product.getCreatedAt())
                .build();
    }

    public ProductEntity createNewProduct(CreateProductRequestDto dto) {

        ProductEntity product = ProductEntity.builder()
                .productName(dto.getProductName())
                .productPrice(dto.getProductPrice())
                .createdAt(LocalDateTime.now())
                .build();

        ProductEntity savedProduct = productRepository.save(product);

        kafkaTemplate.send(
                AppContants.PRODUCT_CREATED_TOPIC,
                savedProduct.getProductId()
        );

        return savedProduct;
    }

    public ProductEntity updateProduct(UpdateProductRequestDto dto,Integer id){
        ProductEntity product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product Don't not exist" + id)
        );
        product.setProductPrice(dto.getProductPrice());
        product.setProductName(dto.getProductName());
        return productRepository.save(product);
    }

    public boolean deleteProduct(Integer id){
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
