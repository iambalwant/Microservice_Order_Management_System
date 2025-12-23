package com.inventoryservice.config.kafkaConfig;


import com.inventoryservice.model.dto.request.KafkaInventoryUpdateRequestDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // String consumer (for plain string producers)
    @Bean
    public ConsumerFactory<String, String> stringConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "inventory-group");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    stringKafkaFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    // JSON consumer (for DTO messages)
    @Bean
    public ConsumerFactory<String, KafkaInventoryUpdateRequestDto> jsonConsumerFactory() {

        JsonDeserializer<KafkaInventoryUpdateRequestDto> deserializer =
                new JsonDeserializer<>(KafkaInventoryUpdateRequestDto.class);

        deserializer.addTrustedPackages("*");
        Map<String, Class<?>> typeMapping = new HashMap<>();
        typeMapping.put(
                "com.orderservice.model.dto.request.KafkaInventoryUpdateRequestDto",
                KafkaInventoryUpdateRequestDto.class
        );
        deserializer.setTypeMapper(new DefaultJackson2JavaTypeMapper() {{
            setIdClassMapping(typeMapping);
        }});

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "inventory-group");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaInventoryUpdateRequestDto>
    jsonKafkaFactory() {

        ConcurrentKafkaListenerContainerFactory<String, KafkaInventoryUpdateRequestDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(jsonConsumerFactory());
        return factory;
    }
}

