package com.example.processor.controller;

import com.example.processor.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain")
public class MyController {
 @Autowired
  CrawlerService crawlerService;
    @GetMapping("/lookup/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String lookup(@PathVariable("name") String name){
        crawlerService.lookup(name);

        return "doing work";
    }

}
