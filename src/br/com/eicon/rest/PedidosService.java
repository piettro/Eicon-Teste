package br.com.eicon.rest;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.eicon.dao.PedidoDAO;
import br.com.eicon.model.Pedido;

@Path("/pedidos")
public class PedidosService {
	private PedidoDAO pedidoDAO;
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	@PostConstruct
	private void init() {
		pedidoDAO = new PedidoDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> listarPedidos(){
		List<Pedido> lista = null;
		
		try {
			lista = pedidoDAO.listarPedidos();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@GET
	@Path("/list/data/{DataCadastro}")
	@Produces(MediaType.APPLICATION_JSON)
	public java.util.List<Pedido> pesquisarPedidosData(@PathParam("DataCadastro") String dataCadastroPesquisa){
		java.util.List<Pedido> lista = null;
		
		try {
			lista = pedidoDAO.buscarPedidoPorData(dataCadastroPesquisa);
		} catch (Exception e){
			e.printStackTrace();
		}	
		return lista;
	}
	
	@GET
	@Path("/list/codigo/{CodigoCliente}")
	@Produces(MediaType.APPLICATION_JSON)
	public java.util.List<Pedido> pesquisarPedidosCodigo(@PathParam("CodigoCliente") int codigoClientePesquisa){
		java.util.List<Pedido> lista = null;
		
		try {
			lista = pedidoDAO.buscarPedidoPorCodigoCliente(codigoClientePesquisa);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@GET
	@Path("/list/numero/{NumeroControle}")
	@Produces(MediaType.APPLICATION_JSON)
	public java.util.List<Pedido> pesquisarPedidos(@PathParam("NumeroControle") int numeroControlePesquisa){
		java.util.List<Pedido> lista = null;
		
		try {
			lista = pedidoDAO.buscarPedidoPorNumeroControle(numeroControlePesquisa);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@GET
	@Path("/list/tudo/{NumeroControle}/{CodigoCliente}/{DataCadastro}")
	@Produces(MediaType.APPLICATION_JSON)
	public java.util.List<Pedido> pesquisarPedidos(@PathParam("NumeroControle") int numeroControlePesquisa,@PathParam("CodigoCliente") int codigoClientePesquisa, @PathParam("DataCadastro") String dataCadastroPesquisa){
		java.util.List<Pedido> lista = null;
		
		try {
			lista = pedidoDAO.buscarPedidoPorTudo(dataCadastroPesquisa, codigoClientePesquisa, numeroControlePesquisa);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarPedido(List<Pedido> pedidosjson){
		String msg = "";
		
		try {
			if (pedidosjson.size() <= 10) {
				for(Pedido pedido : pedidosjson) {
					pedidoDAO.adicionarPedido(pedido);
					System.out.println("Pedido com numero de controle " + pedido.getNumeroControle() + " adicionado com sucesso");
					msg = "Pedido adicionado com sucesso";
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			msg = "Erro ao adicionar o pedido";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarPedido(Pedido pedido, @PathParam("id") int numeroControle) {
		String msg = "";
		System.out.println(pedido.getNome());
		
		try {
			pedidoDAO.editarPedido(pedido,numeroControle);
			msg = "Pedido editado com sucesso";
		} catch (Exception e) {
			msg = "Erro ao editar o pedido";
			e.printStackTrace();
		}
		
		return msg;
		
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletarPedido(@PathParam("id") int numeroControle) {
		String msg = "";
		
		try {
			pedidoDAO.excluirPedido(numeroControle);
			msg = "Pedido deletado com sucesso";
		} catch (Exception e) {
			msg = "Erro ao deletar o pedido";
			e.printStackTrace();
		}
		
		return msg;
	}
}
