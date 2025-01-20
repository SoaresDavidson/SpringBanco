package com.example.poo.banco.service.daviSousaSoares.bar;

import java.util.ArrayList;

public class Conta {
	protected int numConta;
	protected int cpf;
	protected String nomeCliente;
	protected boolean contaFechada;
	protected double valorPago;
	protected double gorjetaComida;
	protected double gorjetaBebida;
	ArrayList<Item> pedidos = new ArrayList<Item>();
	
	public boolean isContaFechada() {
		return this.contaFechada;
	}
	public void setContaFechada(boolean contaFechada) {
		this.contaFechada = contaFechada;
	}


	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Conta(int numConta, int cpf, String nomeCliente) {
		this.numConta = numConta;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
	}
	
	public void addPedido(Item i) throws ContaFechada{
		if (isContaFechada()) throw new ContaFechada();
		pedidos.add(i);
	}

	public ArrayList<Item> getPedidos() {
		return pedidos;
	}
	
	public double getValor() {
		double somaBebidas = 0.0;
		double somaComidas = 0.0;
		double somaItem = 0.0;
		for (Item i : getPedidos()) {
			if (i instanceof Bebida) somaBebidas += i.getValor();
			if (i instanceof Comida) somaComidas += i.getValor();
			if (i instanceof ItemSemgorjeta) somaItem += i.getValor();
		}

		return somaBebidas + (somaBebidas * 0.10) + somaComidas + (somaComidas * 0.15) + somaItem - valorPago;
	
	}

	public void pagamento(double val) {
		this.valorPago += val;
		
	}
	public double getValorPago() {
		return this.valorPago;
	}
	public void setGorjetaComida(double gorjeta) {
		this.gorjetaComida = gorjeta;
	}
	
	public void setGorjetaBebida(double gorjeta) {
		this.gorjetaBebida = gorjeta;
	}

	public double getGorjetaComida() {
		return gorjetaComida;
	}

	public double getGorjetaBebida() {
		return gorjetaBebida;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
		
	}
	public void setPedidos(ArrayList<Item> pedidos) {
		this.pedidos = pedidos;
		
	}


}
