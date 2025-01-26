package com.example.poo.banco.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.poo.banco.DTO.PedidoDTO;
import com.example.poo.banco.service.PedidoService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/add-pedido")
    public ResponseEntity<String> addPedido(@RequestBody PedidoDTO obj) {
        try {
            pedidoService.addPedido(obj);
            return ResponseEntity.ok("Pedido added successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}