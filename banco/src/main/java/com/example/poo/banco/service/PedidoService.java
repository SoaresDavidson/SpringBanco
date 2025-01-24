package com.example.poo.banco.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.poo.banco.model.PedidoModel;
import com.example.poo.banco.repository.PedidoRepository;

public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoModel addPedido(PedidoModel obj){
        return pedidoRepository.save(obj);
    }
}
