package com.microservices.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.model.dto.OrderCdcEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "order_entity",
            groupId = "notification-service"
    )
    public void consume(String message) throws Exception {

        JsonNode node = objectMapper.readTree(message);

        if (node.has("payload")) {
            node = node.get("payload");
        }

        OrderCdcEvent event =
                objectMapper.treeToValue(node, OrderCdcEvent.class);

        handleEvent(event);
    }

    private void handleEvent(OrderCdcEvent event) {

        //New Order Created
        if ("c".equals(event.get__op())) {
            log.info("notification send {} ",event);
        }

        //Payment Completed
        if ("u".equals(event.get__op()) && Boolean.TRUE.equals(event.getPayment_status())) {
            log.info("notification send {} ",event);
        }
    }
}
