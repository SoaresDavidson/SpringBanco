package com.example.poo.banco.service;

public class ItemSemgorjeta extends Item {

	public ItemSemgorjeta(int num, String desc, double valor) {
		super(num, desc, valor);
	}
	
	public double getGorjeta() {
		return 0;
	}

}
