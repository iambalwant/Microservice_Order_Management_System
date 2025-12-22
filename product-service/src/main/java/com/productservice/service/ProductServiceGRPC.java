package com.productservice.service;

import com.example.grpc.OrderRequest;
import com.example.grpc.OrderResponse;
import com.example.grpc.ProductServiceGrpc;
import com.productservice.exception.ResourceNotFoundException;
import com.productservice.model.entity.ProductEntity;
import com.productservice.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;



@Service
@GrpcService
@RequiredArgsConstructor
public class ProductServiceGRPC extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository productRepository;

    @Override
    public void productPrice(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {


        int productId = request.getProductId();
        ProductEntity productDetails = productRepository.findById(productId).orElseThrow(
                () ->new ResourceNotFoundException("Product dont exist")
        );

        OrderResponse build = OrderResponse.newBuilder()
                .setProductPrice(productDetails.getProductPrice().toPlainString())
                .build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();

    }
}
