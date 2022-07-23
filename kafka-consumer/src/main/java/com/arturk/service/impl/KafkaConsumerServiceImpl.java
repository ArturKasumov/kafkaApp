package com.arturk.service.impl;

import com.arturk.service.ConsumerEventHandler;
import com.arturk.service.KafkaConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);

    @Override
    @KafkaListener(id = "listener1",
            groupId = "firstGroup", concurrency = "2",
            topicPartitions = @TopicPartition(topic = "${kafka.topic.name}", partitions = {"0", "1"}))
    public void processRecord(ConsumerRecord record, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId) {
        ConsumerEventHandler handler = ConsumerEventHandler.getHandler(record.value().getClass());
        log.info("Record: {} with key: {} and partitionId: {} was processed", record.value(), record.key(), partitionId);
        handler.process(record.value());
    }

    @KafkaListener(id = "listener2",
            groupId = "firstGroup", concurrency = "1",
            topicPartitions = @TopicPartition(topic = "${kafka.topic.name}", partitions = {"2"}))
    public void processRecord1(ConsumerRecord record, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId) {
        ConsumerEventHandler handler = ConsumerEventHandler.getHandler(record.value().getClass());
        log.info("Record: {} with key: {} and partitionId: {} was processed", record.value(), record.key(), partitionId);
        handler.process(record.value());
    }
}
