package com.orderservice.config.kafkaConfig;


import com.orderservice.config.constant.AppContants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name(AppContants.INVENTORY_UPDATE_TOPIC)
                .build();
    }

}
