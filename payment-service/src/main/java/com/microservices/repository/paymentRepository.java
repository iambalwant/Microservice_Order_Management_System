package com.microservices.repository;

import com.microservices.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
