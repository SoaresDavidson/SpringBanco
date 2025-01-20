package com.example.poo.banco.service.daviSousaSoares.bar;

public abstract class Item {
	protected int num;
	protected String desc;
	protected double valor;
	
	public Item(int num, String desc, double valor) {
		this.num = num;
		this.desc = desc;
		this.valor = valor;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public abstract double getGorjeta();
}
