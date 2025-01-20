package com.example.poo.banco.service.daviSousaSoares.bar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContaDAO {
	PedidoDAO pedidos = new PedidoDAO();
	public ContaDAO() {
		
	}

	public void add(Conta c) throws ClassNotFoundException, SQLException, ContaInexistente, ContaJaCadastrada, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "insert into conta value (" + c.getNumConta() + ", "+ c.getCpf() + ",\'" + c.getNomeCliente() + "\', 0, 0, 0, 0)";
		Statement st = con.createStatement();
		try {
			recuperarConta(c.getNumConta());
			throw new ContaJaCadastrada();
		} catch (ContaInexistente e) {
			st.execute(cmd);
		}
		st.close();
	}
	
	public void add(int numConta, int cpf,String nomeCliente) throws ClassNotFoundException, SQLException, ItemJaCadastrado, ContaJaCadastrada, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "insert into conta value (" + numConta + ", "+ cpf + ",\'" + nomeCliente + "\')";
		Statement st = con.createStatement();
		try {
			recuperarConta(numConta);
			throw new ContaJaCadastrada();
		} catch (ContaInexistente e) {
			st.execute(cmd);
		}
		st.close();	
	}
	
	public void addPedido(int numConta, int numItem, int quant) throws ContaFechada, ClassNotFoundException, SQLException{
		pedidos.inserirItem(numConta, numItem, quant);
	}
	
	public Conta preencheConta(ResultSet rs) throws SQLException, ClassNotFoundException, ItemInexistente {
		int numConta = rs.getInt("numConta");
		int cpf = rs.getInt("cpf");
		String nomeCliente = rs.getString("nomeCliente");
		boolean contaFechada = rs.getBoolean("contaFechada");
		double valorPago = rs.getDouble("valorPago");
		double gorjetaComida = rs.getDouble("gorjetaComida");
		double gorjetaBebida = rs.getDouble("gorjetaBebida");
		Conta c = new Conta(numConta, cpf, nomeCliente);
		c.setContaFechada(contaFechada);
		c.setValorPago(valorPago);
		c.setGorjetaComida(gorjetaComida);
		c.setGorjetaBebida(gorjetaBebida);
		c.setPedidos(pedidos.recuperarTodosConta(c.getNumConta()));
		return c;
	}
	
	public Conta recuperarConta(int num) throws ClassNotFoundException, SQLException, ContaInexistente, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "select * from conta where numConta = " + num;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Conta c = null;
		if (rs.next()) {
			c = preencheConta(rs);
		} else {
			throw new ContaInexistente();
		}
		st.close();
		return c;
	}


	
	public ArrayList<Conta> recuperarTodos() throws SQLException, ClassNotFoundException, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "select * from conta";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Conta c = null;
		ArrayList<Conta> todos =  new ArrayList<Conta>();
		while (rs.next()) {
			c = preencheConta(rs);
			todos.add(c);
		}
		st.close();
		return todos;
	}
	
	public void apagar(int num) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from conta where numConta = " + num;
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public void apagarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from conta where numConta > 0 ";
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public ArrayList<Item> recuperarPedidos(int numConta) throws ClassNotFoundException, SQLException, ItemInexistente {
		return pedidos.recuperarTodosConta(numConta);
	}

	public void apagarTodosPedidos() throws ClassNotFoundException, SQLException {
		pedidos.apagarTodos();		
	}

	public void pagamento(double val, Conta c) throws ClassNotFoundException, SQLException {
		Connection con = Conexao.conectar();
		double valorAtual = val + c.getValorPago();
		String cmd = "update conta set valorPago = " + valorAtual + "where numConta = " + c.getNumConta() ;
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}

	public void update(Conta c) throws ClassNotFoundException, SQLException {
		Connection con = Conexao.conectar();
		String cmd = "UPDATE conta\n"
				+ "SET \n"
				+ "    cpf = "+ c.getCpf() +",\n"
				+ "    nomeCliente = \'"+c.getNomeCliente()+"\',\n"
				+ "    contaFechada = "+ (c.isContaFechada() == true ? 1 : 0)+",\n"
				+ "    valorPago = "+c.getValorPago()+",\n"
				+ "    gorjetaComida = "+c.getGorjetaComida()+",\n"
				+ "    gorjetaBebida = " + c.getGorjetaBebida() + "\n"
				+ "WHERE numConta = "+c.getNumConta()+";";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
		
	}

}
