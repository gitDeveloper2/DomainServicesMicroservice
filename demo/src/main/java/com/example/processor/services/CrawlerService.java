package com.example.processor.services;

import com.example.processor.Domain;
import com.example.processor.DomainList;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CrawlerService {

    private KafkaTemplate<String, Domain> kafkaTemplate;
    private final String TOPIC="web-domains";
    public void lookup(String name) {
        String url="https://api.domainsdb.info/v1/domains/search?domain=" + name ;
                Mono<DomainList> domainListMono=WebClient.create().get().uri(url).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DomainList.class);
        domainListMono.subscribe(domainList ->{
                    domainList.domains.forEach(domain -> {

                    kafkaTemplate.send(TOPIC,domain);
                    System.out.println("Domain message: "+domain.getDomain());
                    });
                }

                );
    }
}
