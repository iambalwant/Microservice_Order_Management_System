package com.microservices.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.model.dto.PaymentCdcEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "payment.notification",
            groupId = "notification-service"
    )
    public void consume(String message) throws Exception {

        JsonNode node = objectMapper.readTree(message);

        // Safety: handle wrapped payload (future-proof)
        if (node.has("payload")) {
            node = node.get("payload");
        }

        PaymentCdcEvent event =
                objectMapper.treeToValue(node, PaymentCdcEvent.class);

        handleEvent(event);
    }

    private void handleEvent(PaymentCdcEvent event) {

        // Ignore snapshot events
        if ("r".equals(event.get__op())) {
            return;
        }

        // Payment initiated
        if ("c".equals(event.get__op())) {
            log.info("🔔 Payment initiated for orderId={} amount={}",
                    event.getOrderId(),
                    event.getPaymentAmount()
            );
        }

        // Payment completed
        if ("u".equals(event.get__op()) && Boolean.TRUE.equals(event.getPaymentStatus())) {
            log.info("✅ Payment SUCCESS for orderId={} amount={}",
                    event.getOrderId(),
                    event.getPaymentAmount()
            );
        }

        // Optional: payment failed
        if ("u".equals(event.get__op()) && Boolean.FALSE.equals(event.getPaymentStatus())) {
            log.warn("❌ Payment FAILED for orderId={}",
                    event.getOrderId()
            );
        }
    }
}
