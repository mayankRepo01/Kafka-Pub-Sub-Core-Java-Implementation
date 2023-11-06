package com.mayank.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.record.Record;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "com.mayank.example.com.mayank.example.customdeserializer.OrderDeserializer");
        props.setProperty("group.id", "OrderGroup");

        KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("mayank"));
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, Order> record : records) {
                System.out.println("Product Name ->" + record.key());
                System.out.println("Consumer name ->" + record.value().consumerName);
                System.out.println("product name ->" + record.value().product);
                System.out.println("quantity name ->" + record.value().quantity);
            }
        }
    }
}
