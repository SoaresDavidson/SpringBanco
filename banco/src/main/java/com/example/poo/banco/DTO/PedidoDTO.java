package com.example.poo.banco.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTO {
    private int id;

    private int num_conta;

    private int num_item;
    
    private int quant;
}
