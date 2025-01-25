package com.example.poo.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poo.banco.DTO.PedidoDTO;
import com.example.poo.banco.model.PedidoModel;
import com.example.poo.banco.repository.PedidoRepository;
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoModel addPedido(PedidoModel obj){
        return pedidoRepository.save(obj);
    }
    
    public PedidoModel addPedido(PedidoDTO obj){
        PedidoModel pedido = convertToEntity(obj); 
        return pedidoRepository.save(pedido);
    }
    private PedidoModel convertToEntity(PedidoDTO pedidoDTO) {
        PedidoModel pedido = new PedidoModel();
        pedido.setNum_conta(pedidoDTO.getNum_conta());
        pedido.setNum_item(pedidoDTO.getNum_item());
        pedido.setQuant(pedidoDTO.getQuant());
        return pedido;
    }

    public void addPedido(int numConta, int numItem, int quant) {
        
    }
}
