package com.abdus.kafka.example;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.Random;

public class SampleKafkaProducer {

  public static void main(String[] args) {
    // Configure properties
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    props.put(ProducerConfig.ACKS_CONFIG, "all");
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = null;
    try{
      producer = new KafkaProducer<String, String>(props);
      TestCallback callback = new TestCallback();
      Random rnd = new Random();
    //  for (long i = 0; i < 100 ; i++) {
      int i =0;
      while(true){
        ProducerRecord<String, String> data = new ProducerRecord<String, String>(
          "test-topic", "key-" + i, "message -hello hi how are you-"+i );
        i++;
        producer.send(data);
      }
    }catch (Exception e){
      e.printStackTrace();

    }finally {
      if (producer != null){
        producer.flush();
        producer.close();
      }


    }

  }

  private static class TestCallback implements Callback {
    //@Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
      if (e != null) {
        try {
          throw  e;
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        System.out.println("Error while producing message to topic :" + recordMetadata + "----" + e.getMessage());
        e.printStackTrace();
      } else {
        String message = String.format("sent message to topic:%s partition:%s  offset:%s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        System.out.println(message);
      }
    }
  }


}
