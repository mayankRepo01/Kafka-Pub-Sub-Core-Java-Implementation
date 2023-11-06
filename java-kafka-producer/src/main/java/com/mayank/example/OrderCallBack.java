package com.mayank.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallBack implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if(e==null){
        System.out.println("Partition name - "+recordMetadata.partition());
        System.out.println("Offset value - "+recordMetadata.offset());
        }else{
        e.printStackTrace();
        }
    }
}
