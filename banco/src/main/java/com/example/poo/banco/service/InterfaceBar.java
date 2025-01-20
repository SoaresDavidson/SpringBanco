package com.example.poo.banco.service;

import java.sql.SQLException;
import java.util.*;

public interface InterfaceBar {
	public void abrirConta(int numConta, int cpf, String nomeCliente) throws ContaAberta, ContaInexistente, DadosInvalidos, ContaJaCadastrada, ClassNotFoundException, SQLException, ItemInexistente;
	public void addPedido(int numConta, int numItem, int quant) throws ContaFechada, ContaInexistente, ItemInexistente, DadosInvalidos, ClassNotFoundException, SQLException;
	public double valorDaConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente;
	public double fecharConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente;
	public void addCardapio(int nu, String n, double x, int tp) throws ItemJaCadastrado, DadosInvalidos;
	public void registrarPagamento(int numConta, double val) throws PagamentoMaior, ContaInexistente, DadosInvalidos, ClassNotFoundException, SQLException, ItemInexistente;
	public ArrayList<Consumo> extratoDeConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente;
}
