package com.example.poo.banco.service.daviSousaSoares.bar;

public class ContaFechada extends Exception {
	public ContaFechada() {
		super("Está conta já foi fechada");
	}
}
