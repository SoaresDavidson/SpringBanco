package com.example.poo.banco.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
@Service
public class Bar implements InterfaceBar{
//	ArrayList<Conta> contas = new ArrayList<Conta>();
	ContaDAO contas = new ContaDAO();
	CardapioDAO cardapio = new CardapioDAO();
	//ArrayList<Item> cardapio = new ArrayList<Item>();
	
	public void abrirConta(int numConta, int cpf, String nomeCliente) throws ContaAberta, ContaInexistente, DadosInvalidos, ContaJaCadastrada, ClassNotFoundException, SQLException, ItemInexistente {
		try {
			pesquisaConta(numConta);
			throw new ContaAberta();
		}catch(ContaInexistente e){
			checaDadosConta(numConta, cpf, nomeCliente);
			Conta c = new Conta(numConta, cpf, nomeCliente);
			try {
				contas.add(c);
			} catch (ClassNotFoundException | SQLException | ContaInexistente | ContaJaCadastrada e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void addPedido(int numConta, int numItem, int quant) throws ContaFechada, ContaInexistente, ItemInexistente, DadosInvalidos, ClassNotFoundException, SQLException {
		Conta c = pesquisaConta(numConta);
		if (c.isContaFechada()) {
			throw new ContaFechada();
		}
		Item i = pesquisaItem(numItem);
		if (numConta <= 0 || numItem <= 0 ||quant <= 0) throw new DadosInvalidos();
		contas.addPedido(c.getNumConta(), i.getNum(), quant);
	}
	
	public double valorDaConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente {
		return contas.recuperarConta(numConta).getValor();
	}
	
	public double fecharConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente {
		Conta c = pesquisaConta(numConta);
		double somaBebidas = 0.0;
		double somaComidas = 0.0;
		double somaItem = 0.0;
		for (Item i : contas.recuperarPedidos(c.getNumConta())) {
			if (i instanceof Bebida) somaBebidas += i.getValor();
			if (i instanceof Comida) somaComidas += i.getValor();
			if (i instanceof ItemSemgorjeta) somaItem += i.getValor();
		}
		c.setContaFechada(true);
		c.setGorjetaComida(somaComidas * 0.15);
		c.setGorjetaBebida(somaBebidas * 0.10);
		contas.update(c);
		return somaBebidas + c.getGorjetaBebida() + somaComidas + c.getGorjetaComida() + somaItem;
	}
	
	public void addCardapio(int nu, String n, double x, int tp) throws ItemJaCadastrado, DadosInvalidos {
		try {
			cardapio.inserirItem(nu, n, x, tp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void registrarPagamento(int numConta, double val) throws PagamentoMaior, ContaInexistente, DadosInvalidos, ClassNotFoundException, SQLException, ItemInexistente {
		Conta c = pesquisaConta(numConta);
		if (val > c.getValor()) throw new PagamentoMaior();
		contas.pagamento(val, c);
	}
	
	public ArrayList<Consumo> extratoDeConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente {
		ArrayList<Consumo> extrato = new ArrayList<Consumo>();
		Conta c = pesquisaConta(numConta);
		for (Item i : c.getPedidos()) {
			extrato.add(new Consumo(i.getNum(), i.getValor(), i.getDesc()));
		}
		extrato.add(new Consumo(0,c.getGorjetaComida() + c.getGorjetaBebida(),"gorjetas"));
		return extrato;
	}
	
	public Conta pesquisaConta(int numConta) throws ContaInexistente, ClassNotFoundException, SQLException, ItemInexistente{
		return contas.recuperarConta(numConta);
		
	}
	
	public Item pesquisaItem(int numItem) throws ItemInexistente, SQLException{
		try {
			return cardapio.recuperarItem(numItem);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ItemInexistente(); //não entendi porque o eclipse reclama com que é obrigado ter esse throw,ele nunca vai chegar nele já que o 
		//recuperar item já da throw ItemInexistente
		
//		for (Item i : cardapio) 
//			if (i.getNum() == numItem)
//				return i;
//		
//		throw new ItemInexistente();
		
		
		
	}
	
	public Consumo pesquisaConsumo(int numItem, ArrayList<Consumo> extrato){
		for (Consumo c : extrato ) 
			if (c.getNum() == numItem)
				return c;
		
		return null;
	}
	
	public void checaDadosConta(int numConta, int cpf, String nomeCliente) throws DadosInvalidos{
		if (numConta <= 0 || cpf <= 0 || nomeCliente.isBlank() || !(nomeCliente instanceof String))
			throw new DadosInvalidos();
		return;
	}
	
	public void checaDadosItem(int nu, String n, double x, int tp) throws DadosInvalidos{
		if (nu <= 0 || tp <= 0 || tp > 3 || n.isBlank() || !(n instanceof String) || x <= 0) {
			throw new DadosInvalidos();
		}
		return;
	}
	
	public void apagaTodoCardapio() throws ClassNotFoundException, SQLException {
		cardapio.apagarTodos();
	}
	public void apagaItemCardapio(int num) throws ClassNotFoundException, SQLException {
		cardapio.apagar(num);
	}

	public void apagarTudo() throws ClassNotFoundException, SQLException {
		contas.apagarTodos();
		contas.apagarTodosPedidos();
		cardapio.apagarTodos();
		
	}
}
