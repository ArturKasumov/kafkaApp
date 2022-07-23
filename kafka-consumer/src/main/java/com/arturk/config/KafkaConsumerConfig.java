package com.arturk.config;

import com.arturk.entity.Alien;
import com.arturk.entity.Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> kafkaProperties() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_TYPE_METHOD, KafkaConsumerConfig.class.getName() + ".defineJavaType");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        return configProps;
    }

    @Bean
    public ConsumerFactory<Integer, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public static JavaType defineJavaType(byte[] bytes, Headers headers) throws IOException {
        JavaType person = TypeFactory.defaultInstance().constructType(Person.class);
        JavaType alien = TypeFactory.defaultInstance().constructType(Alien.class);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        try {
            objectMapper.readValue(bytes, Person.class);
            return person;
        } catch (IOException e) {
            try {
                objectMapper.readValue(bytes, Alien.class);
                return alien;
            } catch (IOException ioException) {
                throw ioException;
            }
        }
    }

}
