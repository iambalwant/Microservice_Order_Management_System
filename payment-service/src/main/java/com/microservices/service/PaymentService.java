package com.microservices.service;


import com.microservices.config.AppContants;
import com.microservices.model.dto.request.KafkaPaymentCreateRequestDto;
import com.microservices.model.entity.PaymentEntity;
import com.microservices.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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


}
