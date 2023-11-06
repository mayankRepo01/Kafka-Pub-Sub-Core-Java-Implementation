package com.mayank.example.com.mayank.example.customdeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayank.example.Order;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;


public class OrderDeserializer implements Deserializer<Order> {

    @Override
    public Order deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper=new ObjectMapper();
        Order order=null;
        try {
            order=objectMapper.readValue(bytes,Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
