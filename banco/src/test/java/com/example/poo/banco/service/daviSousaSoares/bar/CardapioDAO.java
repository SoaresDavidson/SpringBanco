package com.example.poo.banco.service.daviSousaSoares.bar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CardapioDAO {

	public void inserirItem(int num, String nome, double preco, int tipo) throws ClassNotFoundException, SQLException, ItemJaCadastrado {
		Connection con = Conexao.conectar();
		String cmd = "insert into menu value (" + num + ", \'" + nome + "\'," + preco + "," + tipo + ")";
		Statement st = con.createStatement();
		try {
			recuperarItem(num);
			throw new ItemJaCadastrado();
		} catch (ItemInexistente e) {
			st.execute(cmd);
		}
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
	public ArrayList<Item> recuperarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "select * from pessoa";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Item i = null;
		ArrayList<Item> todos =  new ArrayList<Item>();
		while (rs.next()) {
			i = itemTipo(rs);
			todos.add(i);
		}
		st.close();
		return todos;
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
	
	public void apagar(int num) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from menu where num = " + num;
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public void apagarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from menu where num > 0 ";
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}

}

	