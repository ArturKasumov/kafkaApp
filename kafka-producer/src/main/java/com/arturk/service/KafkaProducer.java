package com.arturk.service;

import com.arturk.entity.Alien;
import com.arturk.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String kafkaTopicName;

    @Scheduled(fixedRate = 5000L)
    public void sendAliensToKafkaTopic(){
        Alien alien = new Alien("Mars", 100, "BestShip");
        log.info("Sending alien object : {}", alien);
        kafkaTemplate.send(kafkaTopicName, "1", alien);
        alien = new Alien("Jupiter", 200, "JupShip");
        log.info("Sending alien object : {}", alien);
        kafkaTemplate.send(kafkaTopicName, "1", alien);
    }

    @Scheduled(fixedRate = 5000L)
    public void sendPersonsToKafkaTopic2(){
        Person person = new Person(21, "Artur");
        log.info("Sending person object : {}", person);
        kafkaTemplate.send(kafkaTopicName,"2", person);
        person = new Person(20, "Artur");
        log.info("Sending person object : {}", person);
        kafkaTemplate.send(kafkaTopicName, "2", person);
    }

    @Scheduled(fixedRate = 5000L)
    public void sendEntitiesToKafkaTopic3(){
        Alien alien = new Alien("Sun", 50, "BestShip");
        log.info("Sending alien object : {}", alien);
        kafkaTemplate.send(kafkaTopicName, alien);
        Person person = new Person(22, "Artur");
        log.info("Sending person object : {}", person);
        kafkaTemplate.send(kafkaTopicName, person);
        person = new Person(23, "Artur");
        log.info("Sending person object : {}", person);
        kafkaTemplate.send(kafkaTopicName, person);
    }
}
