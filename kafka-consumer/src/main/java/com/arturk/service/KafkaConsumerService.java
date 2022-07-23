package com.arturk.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumerService {

    void processRecord(ConsumerRecord record, int partitionId);
}
