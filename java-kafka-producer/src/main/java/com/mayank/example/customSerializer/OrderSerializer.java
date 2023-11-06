package com.mayank.example.customSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mayank.example.Order;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerializer implements Serializer<Order> {

    @Override
    public byte[] serialize(String s, Order order) {
        ObjectMapper objectMapper=new ObjectMapper();
        byte[] response=null;
        try {
            response=objectMapper.writeValueAsString(order).getBytes();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public void close() {

    }
}
