package com.orderservice.controller;


import com.orderservice.model.dto.request.OrderRequestDto;
import com.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orderService")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> orderProduct(
            @Valid @RequestBody OrderRequestDto dto
    ) {
        orderService.CreateNewOrder(dto.getProductId(), dto.getQuantity());
        return ResponseEntity.ok("Order created successfully");
    }

}
