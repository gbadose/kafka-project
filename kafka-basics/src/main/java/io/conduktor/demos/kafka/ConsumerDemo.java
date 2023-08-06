package io.conduktor.demos.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConsumerDemo {

    public static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());
    public static void main(String[] args) {
        log.info("I am a Kafka consumer.");


        // create producer properties
        Properties properties = new Properties();

        // connect to localhost
//        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        properties.setProperty("bootstrap.servers","cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"7eqhy6DnSYCV4IrTpDSdzF\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI3ZXFoeTZEblNZQ1Y0SXJUcERTZHpGIiwib3JnYW5pemF0aW9uSWQiOjc1NDQ3LCJ1c2VySWQiOjg3Nzc3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiIxOWI0YzFkNC1lMGJlLTRjMWEtYjRkZS01N2RhMDc4NzJhYmYifX0.AuFk_vqpagtFzamYsNTgS_S2vLYvpPxok2cfzT0aUVA\";");
        properties.setProperty("sasl.mechanism", "PLAIN");


        // create consumer configs
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest");

        //create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        //subscribe to a topic

        //poll for data


    }
}
