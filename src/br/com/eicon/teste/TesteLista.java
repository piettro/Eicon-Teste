package br.com.eicon.teste;

import java.util.List;

import br.com.eicon.dao.PedidoDAO;
import br.com.eicon.model.Pedido;

public class TesteLista {
	public static void main (String [] args){
		String msg = "";
		PedidoDAO pedidoDao = new PedidoDAO();
		
		try {
			msg = "Sucesso ao listar os pedido";
			
			List<Pedido> pedidos = pedidoDao.listarPedidos();
			
			for(Pedido pedido: pedidos) {
				System.out.println("Nome: " + pedido.getNome());
				System.out.println("Número Controle: " + pedido.getNumeroControle());
				System.out.println("Data Cadastro: " + pedido.getDataCadastro());
				System.out.println("Código Cliente: " + pedido.getCodigoCliente());
				System.out.println("Valor: " + pedido.getValor());
				System.out.println("Quantidade: " + pedido.getQuantidade());
				System.out.println("Valor Total: " + pedido.getValorTotal());
				System.out.println("----------------------------------------------");
			}
			
			System.out.println(msg);
			
		} catch (Exception e) {
			msg = "Erro ao listar os pedido";
			System.out.println(msg);
			e.printStackTrace();
		}
	}
}
