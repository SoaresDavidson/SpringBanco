package com.example.poo.banco.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String helloWorld(String name){
        return "hello World " + name;
    }
}
