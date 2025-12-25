package com.microservices.service;


import com.microservices.config.AppContants;
import com.microservices.exception.ResourceNotFoundException;
import com.microservices.model.dto.request.KafkaPaymentCreateRequestDto;
import com.microservices.model.dto.request.PaymentRequestDto;
import com.microservices.model.entity.PaymentEntity;
import com.microservices.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @KafkaListener(topics = AppContants.PAYMENT_CREATE_TOPIC,
            groupId = AppContants.GROUP_ID,
            containerFactory = "jsonKafkaFactory")
    public void createNewPayment(KafkaPaymentCreateRequestDto dto){

        PaymentEntity newPayment = PaymentEntity.builder()
                .createdAt(LocalDateTime.now())
                .paymentAmount(dto.getPaymentAmount())
                .orderId(dto.getOrderId())
                .paymentStatus(false)
                .build();

        paymentRepository.save(newPayment);

    }

    public PaymentEntity getpaymentDetails(Integer orderId){
        return paymentRepository.findByOrderId(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Not payment info found with this paymentId")
        );
    }

    public boolean initiatePayment(PaymentRequestDto request){
        PaymentEntity paymentDetails = paymentRepository.findByPaymentId(request.getPaymentId()).orElseThrow(
                () -> new ResourceNotFoundException("Not payment found with this payment id")
        );
        if(!Objects.equals(paymentDetails.getPaymentAmount(), request.getAmount())){
            return false;
        }
        paymentDetails.setPaymentStatus(true);
        paymentRepository.save(paymentDetails);
        return true;
    }

}
