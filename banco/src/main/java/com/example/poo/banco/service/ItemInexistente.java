package com.example.poo.banco.service;

public class ItemInexistente extends Exception {
	public ItemInexistente() {
		super("Esse item não existe!");
	}
}
