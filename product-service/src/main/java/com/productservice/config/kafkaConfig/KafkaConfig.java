package com.productservice.config.kafkaConfig;


import com.productservice.config.constant.AppContants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name(AppContants.PRODUCT_CREATED_TOPIC)
                .build();
    }

}
