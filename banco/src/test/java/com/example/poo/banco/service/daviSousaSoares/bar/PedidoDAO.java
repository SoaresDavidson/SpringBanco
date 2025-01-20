package com.example.poo.banco.service.daviSousaSoares.bar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PedidoDAO {

	public void inserirItem(int numConta, int numItem, int quant) throws ClassNotFoundException, SQLException {
		Connection con = Conexao.conectar();
		String cmd = "insert into pedido value (" + numConta + "," + numItem + "," + quant + ")";
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public Item recuperarItem(int num) throws ClassNotFoundException, SQLException, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "select * from menu where num = " + num;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Item i = null;
		if (rs.next()) {
			i = itemTipo(rs,num);
		} else {
			throw new ItemInexistente();
		}
		st.close();
		return i;
	}
	
	public ArrayList<Item> recuperarTodosConta(int numConta) throws SQLException, ClassNotFoundException, ItemInexistente {
		Connection con = Conexao.conectar();
		String cmd = "select * from pedido where numConta = " + numConta;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Item i = null;
		ArrayList<Item> todos =  new ArrayList<Item>();
		while (rs.next()) {
			int quant = rs.getInt("quant");
			int numItem = rs.getInt("numItem");
			for (int j = 0; j < quant; j++) {
				i = recuperarItem(numItem);
				i = itemTipo(i);
				todos.add(i);
			}
			
		}
		st.close();
		return todos;
	}
	
	private Item itemTipo(Item i) {
		if (i instanceof ItemSemgorjeta)
			i = new ItemSemgorjeta(i.getNum(), i.getDesc(), i.getValor());
		
		if (i instanceof Bebida)
			i = new Bebida(i.getNum(), i.getDesc(), i.getValor());
		
		if (i instanceof Comida)
			i = new Comida(i.getNum(), i.getDesc(), i.getValor());
		
		return i;
	}

	public Item itemTipo(ResultSet rs) throws SQLException {
		int num = rs.getInt("num");
		String nome = rs.getString("nome");
		double preco = rs.getDouble("preco");
		int tipo = rs.getInt("tipo");
		Item i = null;
		if (tipo == 1)
			i = new ItemSemgorjeta(num, nome, preco);
		if (tipo == 2)
			i = new Bebida(num, nome, preco);
		if (tipo == 3)
			i = new Comida(num, nome, preco);
		
		return i;
	}
	
	public Item itemTipo(ResultSet rs,int num) throws SQLException {
		String nome = rs.getString("nome");
		double preco = rs.getDouble("preco");
		int tipo = rs.getInt("tipo");
		Item i = null;
		if (tipo == 1)
			i = new ItemSemgorjeta(num, nome, preco);
		if (tipo == 2)
			i = new Bebida(num, nome, preco);
		if (tipo == 3)
			i = new Comida(num, nome, preco);
		
		return i;
	}
	
	public void apagar(int numConta) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from pedido where numConta = " + numConta;
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public void apagarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from pedido where numConta > 0 ";
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}


}

