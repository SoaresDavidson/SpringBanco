package com.example.poo.banco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.DTO.PedidoDTO;
import com.example.poo.banco.service.PedidoService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/{num_conta}/add-pedido")
    public ResponseEntity<String> addPedido(
            @PathVariable int num_conta,
            @RequestParam int num_item,
            @RequestParam int quant) {
        try {
            pedidoService.addPedido(num_conta, num_item, quant);
            return ResponseEntity.ok("Pedido added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}