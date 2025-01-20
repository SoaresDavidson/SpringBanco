package com.example.poo.banco.service;

public class ContaJaCadastrada extends Exception {
	public ContaJaCadastrada() {
		super("Conta já cadastrada");
	}
}
