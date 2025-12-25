package com.microservices.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @SequenceGenerator(
            name = "payment_id_sequence",
            sequenceName = "payment_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_id_sequence"
    )
    private Integer paymentId;
    private Integer orderId;
    private BigDecimal paymentAmount;
    private boolean paymentStatus;
    private LocalDateTime createdAt;

}
