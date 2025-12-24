package com.microservices.model.dto;

import lombok.Data;

@Data
public class OrderCdcEvent {

    private Integer order_id;
    private Long created_at;
    private String payment_amount;   // base64 decimal
    private Boolean payment_status;
    private Integer product_id;

    private String __op;
    private String __table;
    private Long __ts_ms;
}