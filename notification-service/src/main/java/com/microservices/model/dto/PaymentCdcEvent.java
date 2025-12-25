package com.microservices.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentCdcEvent {

    @JsonProperty("payment_id")
    private Long paymentId;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("payment_amount")
    private String paymentAmount;

    @JsonProperty("payment_status")
    private Boolean paymentStatus;

    @JsonProperty("__op")
    private String __op;

    @JsonProperty("__table")
    private String __table;

    @JsonProperty("__ts_ms")
    private Long __tsMs;
}