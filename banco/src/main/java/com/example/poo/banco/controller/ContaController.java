package com.example.poo.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.DTO.ContaDTO;
import com.example.poo.banco.model.ContaModel;
import com.example.poo.banco.service.ContaService;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/create-conta")
    public ResponseEntity<String> createConta(@RequestBody ContaDTO obj) {
        contaService.saveConta(obj);
        return ResponseEntity.ok("Conta criada com sucesso");
    }

    @PostMapping("/{num_conta}/add-pedido")
    public ResponseEntity<String> addPedido(
            @PathVariable int numConta,
            @RequestParam int numItem,
            @RequestParam int quant) {
        try {
            contaService.addPedido(numConta, numItem, quant);
            return ResponseEntity.ok("Pedido added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


