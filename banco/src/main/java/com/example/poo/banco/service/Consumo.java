package com.example.poo.banco.service;

public class Consumo {
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	protected int num;
	protected double valorUnit;
	protected double valorTotal;
	protected int quant;
	protected String desc;
	
	public Consumo(int num ,double valorUnit,String desc) {
		this.num = num;
		this.valorUnit = valorUnit;
		this.desc = desc;
	}

	public double getValorUnit() {
		return valorUnit;
	}

	public double getValorTotal() {
		return valorTotal;
	}
	
	public void calculaValorTotal(double valorUnit, int quant) {
		this.valorTotal = valorUnit*quant;
	}

	public int getQuant() {
		return quant;
	}
	public void aumentaQuant() {
		this.quant++;
	}

	public String getDesc() {
		return desc;
	}

}
