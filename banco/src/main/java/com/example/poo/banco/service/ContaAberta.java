package com.example.poo.banco.service;

public class ContaAberta extends Exception {

	public ContaAberta() {
		super("conta já cadastrada!");
	}

}
