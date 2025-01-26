package com.example.poo.banco.service.exceptions;

public class ContaInexistente extends Exception{

    public ContaInexistente(){
        super("Conta não existe");
    }
}
