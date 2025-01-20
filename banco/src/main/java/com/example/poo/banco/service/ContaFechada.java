package com.example.poo.banco.service;

public class ContaFechada extends Exception {
	public ContaFechada() {
		super("Está conta já foi fechada");
	}
}
