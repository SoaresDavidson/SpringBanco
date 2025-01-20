package com.example.poo.banco.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.service.Bar;
import com.example.poo.banco.service.HelloWorldService;
import com.example.poo.banco.service.Item;
import com.example.poo.banco.service.ItemInexistente;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;
    
    @Autowired
    private Bar bar;

    @GetMapping
    public String hellowWorld() throws ItemInexistente, SQLException{
        Item d = bar.pesquisaItem(1);
        return helloWorldService.helloWorld(d.getClass().getName());
    }
}
