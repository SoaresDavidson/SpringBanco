package com.example.poo.banco.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    private int numConta;
    private int cpf;
    private String nomeCliente;
    private boolean contaFechada;
    private double valorPago;
    private double gorjetaComida;
    private double gorjetaBebida;
}
