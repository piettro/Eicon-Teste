package br.com.eicon.teste;

import java.math.BigDecimal;

import br.com.eicon.dao.PedidoDAO;
import br.com.eicon.model.Pedido;

public class TesteAdiciona {
	public static void main (String [] args){
		String msg = "";
		BigDecimal valor = new BigDecimal("1.5"); 
		int codigoCliente = 18685;
		int numeroControle = 33;
		String dataCadastro = "2010/12/27";
		String nome = "borracha";
		int quantidade;
		PedidoDAO pedidoDAO = new PedidoDAO();
		
		try {
			Pedido pedido = new Pedido(numeroControle,dataCadastro,nome,valor,quantidade,codigoCliente);
			pedidoDAO.adicionarPedido(pedido);
			msg = "Sucesso ao adicionar o pedido";
			System.out.println(msg);			
		} catch (Exception e) {
			msg = "Erro ao adicionar o pedido";
			System.out.println(msg);
			e.printStackTrace();
		}
	}
}
