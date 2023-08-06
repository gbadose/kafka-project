package io.conduktor.demos.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {

    public static final Logger log = LoggerFactory.getLogger(ProducerDemoKeys.class.getSimpleName());
    public static void main(String[] args) {
        log.info("I am a Kafka producer.");


        // create producer properties
        Properties properties = new Properties();

        // connect to localhost
//        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        properties.setProperty("bootstrap.servers","cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"7eqhy6DnSYCV4IrTpDSdzF\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI3ZXFoeTZEblNZQ1Y0SXJUcERTZHpGIiwib3JnYW5pemF0aW9uSWQiOjc1NDQ3LCJ1c2VySWQiOjg3Nzc3LCJmb3JFeHBpcmF0aW9uQ2hlY2siOiIxOWI0YzFkNC1lMGJlLTRjMWEtYjRkZS01N2RhMDc4NzJhYmYifX0.AuFk_vqpagtFzamYsNTgS_S2vLYvpPxok2cfzT0aUVA\";");
        properties.setProperty("sasl.mechanism", "PLAIN");

        //set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int j=0; j<2; j++){

            for (int i=0; i<10; i++){

                String topic = "demo_java";
                String key = "id_" + i;
                String value = "This is awesome " + i;


                // create producer record
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

                // send data
                producer.send(producerRecord, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        // executes every time a record is sent successfully or an exception is thrown
                        if (e == null){
                            // successful
                            log.info("Key: " + key + " | Partition: " + metadata.partition());
                        } else{
                            log.error("Error while producing", e);
                        }

                    }
                });

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // flush and close producer
        producer.flush();
        producer.close();

    }
}
