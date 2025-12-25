package com.microservices.repository;

import com.microservices.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    Optional<PaymentEntity> findByPaymentId(Integer paymentId);
    Optional<PaymentEntity> findByOrderId(Integer orderId);

}
