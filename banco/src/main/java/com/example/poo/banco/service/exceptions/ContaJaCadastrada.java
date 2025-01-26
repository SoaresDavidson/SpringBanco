package com.example.poo.banco.service.exceptions;

public class ContaJaCadastrada extends Exception{

    public ContaJaCadastrada(){
        super("Conta já cadastrada!");
    }
}
