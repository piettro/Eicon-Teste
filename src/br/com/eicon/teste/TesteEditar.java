package br.com.eicon.teste;

import java.math.BigDecimal;

import br.com.eicon.dao.PedidoDAO;
import br.com.eicon.model.Pedido;

public class TesteEditar {
	public static void main (String [] args){
		String msg = "";
		BigDecimal valor = new BigDecimal("5.5"); 
		int codigoCliente = 15;
		int numeroControle = 25;
		String dataCadastro = "13/12/2020";
		String nome = "caneta";
		int quantidade = 30;
		PedidoDAO pedidoDAO = new PedidoDAO();
		
		try {
			Pedido pedido = new Pedido(numeroControle,dataCadastro,nome,valor,quantidade,codigoCliente);
			pedidoDAO.editarPedido(pedido, 25);
			msg = "Sucesso ao editar o pedido";
			System.out.println(msg);
			
		} catch (Exception e) {
			msg = "Erro ao editar o pedido";
			System.out.println(msg);
			e.printStackTrace();
		}
	}
}
