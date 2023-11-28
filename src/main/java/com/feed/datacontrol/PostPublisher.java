package com.feed.datacontrol;

import com.feed.model.UserAccount;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PostPublisher {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer(properties);

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("john");
        userAccount.addFriend("eva");

        String[] posts = {"Hello Everyone" , "This is my postFlow page" , "Follow me !!", "thanks!"};

        for (String post: posts) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("flow-post", userAccount.getUsername(), post);
            producer.send(producerRecord, (metadata, exception) -> {
                if(exception == null ){
                    System.out.println("Post sent successfully : Topic=> "+metadata.topic());
                    System.out.println("Partition => "+metadata.partition());
                    System.out.println("Offset => "+metadata.offset());
                } else {
                    System.err.println("Error publishing post => "+exception.getMessage());
                }
            });
        }
        producer.close();
    }
}
