package com.example.poo.banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoModel {

    @Id
    @Column(name = "id")
    private int id;
    
    @Column(name = "num_conta")
    private int num_conta;

    @Column(name = "num_item")
    private int num_item;
    
    @Column(name = "quant")
    private int quant;
}
