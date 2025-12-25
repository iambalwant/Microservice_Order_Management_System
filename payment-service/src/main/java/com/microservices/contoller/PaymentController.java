package com.microservices.contoller;


import com.microservices.model.dto.request.PaymentRequestDto;
import com.microservices.model.dto.responce.GetPaymentDetailDto;
import com.microservices.model.entity.PaymentEntity;
import com.microservices.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@Validated
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<GetPaymentDetailDto> paymentDetails(
            @RequestParam("orderId")
            @NotBlank(message = "orderId is required")
            String orderId
    ){
        Integer id = Integer.parseInt(orderId);
        PaymentEntity paymentEntity = paymentService.getpaymentDetails(id);
        GetPaymentDetailDto response = GetPaymentDetailDto.builder()
                .paymentAmount(paymentEntity.getPaymentAmount())
                .paymentStatus(paymentEntity.isPaymentStatus())
                .paymentId(paymentEntity.getPaymentId())
                .orderId(paymentEntity.getOrderId())
                .createdAt(paymentEntity.getCreatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> payment(
            @RequestBody
            @Valid
            PaymentRequestDto dto
    ){
        boolean isPaymentDone = paymentService.initiatePayment(dto);
        if(isPaymentDone) {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.PAYMENT_REQUIRED);
    }


}
