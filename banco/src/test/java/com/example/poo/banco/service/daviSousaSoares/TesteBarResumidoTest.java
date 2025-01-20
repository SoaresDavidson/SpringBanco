package com.example.poo.banco.service.daviSousaSoares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poo.banco.service.Bar;
import com.example.poo.banco.service.Consumo;
import com.example.poo.banco.service.ContaAberta;
import com.example.poo.banco.service.ContaFechada;
import com.example.poo.banco.service.ContaInexistente;
import com.example.poo.banco.service.ContaJaCadastrada;
import com.example.poo.banco.service.DadosInvalidos;
import com.example.poo.banco.service.ItemInexistente;
import com.example.poo.banco.service.ItemJaCadastrado;
import com.example.poo.banco.service.PagamentoMaior;

@SpringBootTest
public class TesteBarResumidoTest {
    @Test
	public void testarContaSimples() throws ContaAberta, ContaFechada, ContaInexistente, DadosInvalidos, ItemJaCadastrado, ItemInexistente, ClassNotFoundException, SQLException, ContaJaCadastrada{
		Bar b = new Bar();
		b.apagarTudo();
		b.addCardapio(1, "Cerveja Brahma", 5.5, 2);
		b.addCardapio(2, "File com Fritas", 28, 3);
		b.abrirConta(1, 1, "Pedro");
		
		//3 pedidos de cerveja
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		//Pedido de file
		b.addPedido(1, 2, 1);
		
		double val = b.fecharConta(1);
		//# cervejas = 33 + 3,30; file = 28 + 4.2; Total = 68,5
		assertEquals(68.5, val, 0.0001);
	}

	@Test
	public void testarInserirContaFechada() throws ContaAberta, ContaInexistente, ContaFechada, DadosInvalidos, ItemJaCadastrado, ItemInexistente, ClassNotFoundException, SQLException, ContaJaCadastrada{
		Bar b = new Bar();
		b.apagarTudo();
		b.addCardapio(1, "Cerveja Brahma", 5.5, 2);
		b.addCardapio(2, "File com Fritas", 28, 3);
		b.abrirConta(1, 1, "Pedro");
		
		//3 pedidos de cerveja
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		//Pedido de filŽ 
		b.addPedido(1, 2, 1);
		
		double val = b.fecharConta(1);
		//# cervejas = 33; file = 28; subtotal = 61; 10% = 6.1
		
		try {
			b.addPedido(1, 2, 1);
			fail("Deveria ter dado exce�‹o de conta fechada.");
		} catch (ContaFechada e) {
			// Valor n‹o foi alterado
			assertEquals(68.5, b.valorDaConta(1), 0.0001);
		}
	}
	
	@Test
	public void testarPagarContaEmPartes() throws ContaAberta, ContaInexistente, ContaFechada, PagamentoMaior, DadosInvalidos, ItemJaCadastrado, ItemInexistente, ClassNotFoundException, SQLException, ContaJaCadastrada{
		Bar b = new Bar();
		b.apagarTudo();
		b.addCardapio(1, "Cerveja Brahma", 5.5, 2);
		b.addCardapio(2, "File com Fritas", 28, 3);
		
		b.abrirConta(1, 1, "Pedro");
		
		//3 pedidos de cerveja
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		b.addPedido(1, 1, 2);
		//Pedido de filŽ 
		b.addPedido(1, 2, 1);
		
		double val = b.fecharConta(1);
		//# cervejas = 33; file = 28; subtotal = 61; 10% = 6.1

		b.registrarPagamento(1, 20);
		b.registrarPagamento(1, 20);
		b.registrarPagamento(1, 20);
		b.registrarPagamento(1, 8.1);
		
		try {
			b.registrarPagamento(1, 0.50);
            fail("Deveria ter dado exce�‹o.");
		} catch (PagamentoMaior e1) {
			assertEquals(0.4, b.valorDaConta(1), 0.001);
		}
	}
	
	@Test
	public void testarContaInexistente() throws ContaAberta, ContaFechada, PagamentoMaior, ContaInexistente, DadosInvalidos, ItemJaCadastrado, ItemInexistente, ClassNotFoundException, SQLException, ContaJaCadastrada{
		Bar b = new Bar();
		b.apagarTudo();
		b.addCardapio(1, "Cerveja Brahma", 5.5, 2);
		b.addCardapio(2, "File com Fritas", 28, 3);
		
		b.abrirConta(1, 1, "Pedro");
		b.abrirConta(2, 2, "Raimundo");
		b.abrirConta(3, 3, "Maria");

		try {
			b.addPedido(4, 2, 1);
			fail("N‹o era para ter registrado pedido em conta inexistente!");
		} catch (ContaInexistente e) {
          // N‹o era para ter registrado pedido em conta inexistente!
		}
		
		try {
			double val = b.fecharConta(4);
			fail("N‹o era para fechar conta inexistente!");
		} catch (ContaInexistente e) {
			// N‹o era para fechar conta inexistente!
		}

		try {
			b.registrarPagamento(4, 0.50);
            fail("Deveria ter dado exce�‹o de conta inexistente.");
		} catch (ContaInexistente e1) {
			//Deveria ter dado exce�‹o de conta inexistente.
		}
	}
	
	@Test
	public void testarContaJaExistente() throws ContaInexistente, ContaAberta, DadosInvalidos, ClassNotFoundException, SQLException, ContaJaCadastrada, ItemInexistente{
		Bar b = new Bar();
		b.apagarTudo();
		b.abrirConta(1, 1, "Pedro");
		try {
			b.abrirConta(1, 2, "Raimundo");
			fail("Deveria ter dado exce�‹o de conta j‡ aberta.");
		} catch (ContaAberta e) {
            // Excecao correta.
		}
		b.fecharConta(1);
	}
	
	@Test
	public void testarExtratoItens() throws ContaAberta, ContaFechada, PagamentoMaior, ContaInexistente, DadosInvalidos, ItemJaCadastrado, ItemInexistente, ClassNotFoundException, SQLException, ContaJaCadastrada{
		Bar b = new Bar();
		b.apagarTudo();
		b.addCardapio(1, "Cerveja Brahma", 5.5, 2);
		b.addCardapio(2, "File com Fritas", 28, 3);
		
		b.abrirConta(1, 1, "Pedro");
		b.addPedido(1, 1, 1);
		b.addPedido(1, 1, 1);
		b.addPedido(1, 1, 1);
		b.addPedido(1, 2, 1);
		double val = b.fecharConta(1);
		ArrayList<Consumo> itens = b.extratoDeConta(1);
		System.out.println(itens.size());
		assertEquals(5, itens.size());
	}
}
