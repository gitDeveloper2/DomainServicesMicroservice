package com.example.demo.services;

import com.example.demo.services.dto.Domain;
import com.example.demo.services.dto.DomainList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
                    });
                }

                );
    }
}
