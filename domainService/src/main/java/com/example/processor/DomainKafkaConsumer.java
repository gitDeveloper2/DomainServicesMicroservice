package com.example.processor;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {
    @Bean
    public Consumer<KStream<String, Domain>> domainConsumer(){
       return kstream->kstream.foreach((key,domain)->{
           System.out.println("in consumer"+domain.getDomain()+"status"+domain.getIsDead());
       });
    }
}
