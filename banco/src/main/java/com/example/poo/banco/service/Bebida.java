package com.example.poo.banco.service;

public class Bebida extends Item {

	public Bebida(int num, String desc, double valor) {
		super(num, desc, valor);
	}
	
	public double getGorjeta() {
		return valor * 0.10;
	}

}
