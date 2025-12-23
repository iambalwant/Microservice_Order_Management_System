package com.orderservice.service;


import com.example.grpc.OrderRequest;
import com.example.grpc.OrderResponse;
import com.example.grpc.ProductServiceGrpc;
import com.orderservice.model.entity.OrderEntity;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService{

    @GrpcClient("msProductService")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    private final OrderRepository orderRepository;

    public OrderResponse getProductPrice(int productID){
        OrderRequest build = OrderRequest.newBuilder().setProductId(productID).build();
        return serviceBlockingStub.productPrice(build);
    }

    public void CreateNewOrder(Integer productId,Integer quantity){

        OrderResponse response = getProductPrice(productId);

        BigDecimal productPrice =
                new BigDecimal(response.getProductPrice());
        BigDecimal paymentAmount = productPrice.multiply(BigDecimal.valueOf(quantity));

        OrderEntity newOrder = OrderEntity.builder()
                .productId(productId)
                .paymentStatus(false)
                .paymentAmount(paymentAmount)
                .createdAt(LocalDateTime.now())
                .build();

        orderRepository.save(newOrder);
    }


}
