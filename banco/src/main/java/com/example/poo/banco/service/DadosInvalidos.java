package com.example.poo.banco.service;

public class DadosInvalidos extends Exception {
	public DadosInvalidos() {
		super("Prenchimento com algum dado invalido!");
	}
}
