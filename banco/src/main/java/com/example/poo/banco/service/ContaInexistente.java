package com.example.poo.banco.service;

public class ContaInexistente extends Exception {
	public ContaInexistente() {
		super("A conta não existe!");
	}
}
