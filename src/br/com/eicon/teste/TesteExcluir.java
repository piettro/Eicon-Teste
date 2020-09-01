package br.com.eicon.teste;


import br.com.eicon.dao.PedidoDAO;


public class TesteExcluir {
	public static void main (String [] args){
		String msg = "";
		PedidoDAO pedidoDao = new PedidoDAO();
		
		try {
			pedidoDao.excluirPedido(33);
			msg = "Sucesso ao excluir o pedido";
			System.out.println(msg);
		} catch (Exception e) {
			msg = "Erro ao deletar o pedido";
			System.out.println(msg);
			e.printStackTrace();
		}
	}
}
