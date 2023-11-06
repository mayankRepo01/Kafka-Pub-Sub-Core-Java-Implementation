package com.mayank.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props=new Properties();
        props.setProperty("bootstrap.servers","localhost:9092");
        props.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
//        props.setProperty("value.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        props.setProperty("value.serializer","com.mayank.example.customSerializer.OrderSerializer");
        props.setProperty(ProducerConfig.,"com.mayank.example.customSerializer.OrderSerializer");


        KafkaProducer<String,Order> kafkaProducer=new KafkaProducer<String, Order>(props);
        Order order=new Order();
        order.setConsumerName("Apple");
        order.setProduct("Iphone13");
        order.setQuantity(5);
        ProducerRecord<String,Order> producerRecord=new ProducerRecord<>("mayank","Mac Book",order);

        try {
            /* fire and forget way
            kafkaProducer.send(producerRecord);
             */
            /* using future object and waiting for response

            RecordMetadata recordMetadata=kafkaProducer.send(producerRecord).get();
            System.out.println("Message Sent Successfully");
            System.out.println("Partition name - "+recordMetadata.partition());
            System.out.println("Offset value - "+recordMetadata.offset());
            */
            kafkaProducer.send(producerRecord,new OrderCallBack());
            System.out.println("Message Sent Successfully");
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}
