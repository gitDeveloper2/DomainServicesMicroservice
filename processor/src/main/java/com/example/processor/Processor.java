package com.example.processor;

import com.example.processor.Domain;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration
public class Processor {
    @Bean
    public Function<KStream<String,Domain>,KStream<String, Domain>> domainProcessor(){
        return kstream->
            kstream.filter((key, domain)->{
                if(domain.getIsDead()){
                    System.out.println("Domain id dead"+domain.getDomain());
                }else
                {
                    System.out.println("Domain is alive"+domain.getDomain());
                }
                return !domain.getIsDead();
            });

    }


}
