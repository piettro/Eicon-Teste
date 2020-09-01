package br.com.eicon.dao;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.eicon.config.BDConfig;
import br.com.eicon.model.Pedido;

public class PedidoDAO {
	public void adicionarPedido(Pedido pedido) throws Exception{
		String sql = "INSERT INTO pedidos (NUMEROCONTROLE,DATACADASTRO,NOME,VALOR,QUANTIDADE,CODIGOCLIENTE,VALORTOTAL) values (?,?,?,?,?,?,?)";
		Connection conexao = (Connection) BDConfig.getConnection();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, pedido.getNumeroControle());
			stmt.setDate(2, pedido.getDataCadastro2());
			stmt.setString(3, pedido.getNome());
			stmt.setBigDecimal(4, pedido.getValor());
			stmt.setInt(5, pedido.getQuantidade());
			stmt.setInt(6, pedido.getCodigoCliente());
			stmt.setBigDecimal(7, pedido.getValorTotal());
			
			stmt.execute();
			stmt.close();
				
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void editarPedido(Pedido pedido, int numeroControle) throws Exception{
		Connection conexao = (Connection) BDConfig.getConnection();
		String sql = "UPDATE pedidos SET DATACADASTRO = ? ,NOME = ?,VALOR = ?,QUANTIDADE =?,CODIGOCLIENTE = ?, VALORTOTAL = ? WHERE NUMEROCONTROLE = ?";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(7, numeroControle);
			stmt.setDate(1, pedido.getDataCadastro2());
			stmt.setString(2, pedido.getNome());
			stmt.setBigDecimal(3, pedido.getValor());
			stmt.setInt(4, pedido.getQuantidade());
			stmt.setInt(5, pedido.getCodigoCliente());
			stmt.setBigDecimal(6, pedido.getValorTotal());
			
			stmt.execute();
			stmt.close();
				
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void excluirPedido(int numerocontrole) throws Exception{
		Connection conexao = (Connection) BDConfig.getConnection();
		String sql = "DELETE FROM pedidos WHERE NUMEROCONTROLE = ?";
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, numerocontrole);
			stmt.execute();
			stmt.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Pedido> listarPedidos() throws Exception {
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		String sql = "SELECT * FROM pedidos";
		listaBusca = carregarLista(sql,"0",0,0);
		return listaBusca;
	}
		
	public List<Pedido> buscarPedidoPorData(String dataCadastroPesquisa){
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		String sql = "SELECT * FROM pedidos WHERE DATACADASTRO = ?";
		listaBusca = carregarLista(sql,dataCadastroPesquisa,0,0);
		return listaBusca;
	}
	
	public List<Pedido> buscarPedidoPorNumeroControle(int numeroControlePesquisa){
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		String sql = "SELECT * FROM pedidos WHERE NUMEROCONTROLE = ?";
		listaBusca = carregarLista(sql,"0",0,numeroControlePesquisa);
		return listaBusca;
	}
	
	public List<Pedido> buscarPedidoPorCodigoCliente(int codigoClientePesquisa){
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		String sql = "SELECT * FROM pedidos WHERE CODIGOCLIENTE = ?";
		listaBusca = carregarLista(sql,"0",codigoClientePesquisa,0);
		return listaBusca;
	}
		
	public List<Pedido> buscarPedidoPorTudo(String dataCadastroPesquisa,int codigoClientePesquisa,int numeroControlePesquisa){
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		String sql = "SELECT * FROM pedidos WHERE (DATACADASTRO = ? AND CODIGOCLIENTE = ? AND NUMEROCONTROLE = ?)";
		listaBusca = carregarLista(sql,dataCadastroPesquisa,codigoClientePesquisa,numeroControlePesquisa);
		return listaBusca;
	}
	
	public List<Pedido> carregarLista(String sql,String dataCadastroPesquisa,int codigoClientePesquisa,int numeroControlePesquisa) {
		List<Pedido> listaBusca = new ArrayList<Pedido>();
		
		try {
			Connection conexao = (Connection) BDConfig.getConnection();
			
			PreparedStatement statement = conexao.clientPrepareStatement(sql);
			statement = conexao.clientPrepareStatement(sql);
	
			if (dataCadastroPesquisa != "0" && codigoClientePesquisa != 0 && numeroControlePesquisa != 0) {
				statement.setString(1, dataCadastroPesquisa); 
				statement.setInt(2, codigoClientePesquisa);
				statement.setInt(3, numeroControlePesquisa);
			} else if (dataCadastroPesquisa != "0") {
				statement.setString(1, dataCadastroPesquisa);
			} else if (codigoClientePesquisa != 0) {
				statement.setInt(1, codigoClientePesquisa);
			} else if (numeroControlePesquisa != 0) {
				statement.setInt(1, numeroControlePesquisa);
			}
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {		
				int codigoCliente = rs.getInt("CODIGOCLIENTE");
				int numeroControle=rs.getInt("NUMEROCONTROLE");
				String dataCadastro= rs.getString("DATACADASTRO");
				String nome=rs.getString("NOME");
				BigDecimal  valor=rs.getBigDecimal("VALOR");
				int quantidade=rs.getInt("QUANTIDADE");
			
				Pedido pedido = new Pedido(numeroControle,dataCadastro,nome,valor,quantidade,codigoCliente);
				listaBusca.add(pedido);
			}
			
			rs.close();
			statement.close();
			return listaBusca;

		} catch (Exception e) {
			throw new RuntimeException(e);	
		}
	}
}
