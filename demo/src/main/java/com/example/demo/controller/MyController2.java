package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController

public class MyController2 {
    @GetMapping("/add")
    public ArrayList<Integer> add(){

        ArrayList<Integer> list=new ArrayList<>(Arrays.asList(1,2,3,4,5));
        list.add(98);

        return list;

    }


}
