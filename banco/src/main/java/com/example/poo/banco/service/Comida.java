package com.example.poo.banco.service;

public class Comida extends Item {

	public Comida(int num, String desc, double valor) {
		super(num, desc, valor);
	}
	
	public double getGorjeta() {
		return valor * 0.15;
	}

}
