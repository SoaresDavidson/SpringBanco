package com.example.poo.banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
@Table(name = "pedido")
@Entity
@Getter
@Setter
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "num_conta")
    private int num_conta;


    @Column(name = "num_item")
    private int num_item;


    @Column(name = "quant")
    private int quant;
}
