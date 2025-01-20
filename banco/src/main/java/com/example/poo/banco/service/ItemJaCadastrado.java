package com.example.poo.banco.service;

public class ItemJaCadastrado extends Exception {
	public ItemJaCadastrado() {
		super("Esse item ja foi cadastrado!");
	}
}
