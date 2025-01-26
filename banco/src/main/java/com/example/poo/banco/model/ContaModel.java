package com.example.poo.banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Table(name = "conta")
@Entity
@Getter
@Setter
public class ContaModel {

    @Id
    @Column(name = "num_conta")
    private int numConta;
    
    @Column(name = "cpf")
    private int cpf;

    @NotBlank(message = "Insira um nome válido!")
    @Column(name = "nomeCliente")
    private String nomeCliente;

    @Column(name = "contaFechada",columnDefinition = "boolean default false")
    private boolean contaFechada;

    @Column(name = "valorPago")
    private double valorPago;

    @Column(name = "gorjetaComida")
    private double gorjetaComida;

    @Column(name = "gorjetaBebida")
    private double gorjetaBebida;


}
