package br.com.eicon.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Pedido{

	private int numeroControle;

    private String dataCadastro;
	private Date dataCadastro2;
    private String nome;
    private BigDecimal valor;
    private int quantidade;
    private int codigoCliente;
    private BigDecimal valorTotal;
    
    public Pedido() {
		
	}
    
    public Pedido (int numeroControle,String dataCadastro,String nome,BigDecimal valor,int quantidade,int codigoCliente) throws ParseException {
    	super();
    	
    	this.numeroControle = numeroControle;
       	this.dataCadastro = dataCadastro;
       	this.nome = nome;
        this.valor = valor;
       	this.quantidade = quantidade;
       	this.codigoCliente = codigoCliente;
    }
    
    public int getNumeroControle() {
		return numeroControle;
	}

	public void setNumeroControle(int numeroControle) {
		this.numeroControle = numeroControle;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}
	
	public Date getDataCadastro2() {
		try {
			return formataData(dataCadastro);
		} catch (ParseException e) {
			System.out.println("Passou por aqui dataCadastro2");
			e.printStackTrace();
		}
		
		return dataCadastro2;
	}

//	public void setDataCadastro(Date dataCadastro) {
//		this.dataCadastro = dataCadastro;
//	}
	
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		System.out.println(quantidade);
		return verificaQuantidade(quantidade);
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public BigDecimal getValorTotal() {
		return calcularValorTotal();
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

    private BigDecimal calcularValorTotal(){
        if(this.quantidade > 10){
        	this.valorTotal = valor.multiply(new BigDecimal(quantidade));
        	this.valorTotal = valorTotal.multiply(new BigDecimal("0.9"));   
        } else if (this.quantidade > 5){
        	this.valorTotal = valor.multiply(new BigDecimal(quantidade));
        	this.valorTotal = valorTotal.multiply(new BigDecimal("0.95"));
        } else {
        	this.valorTotal = valor.multiply(new BigDecimal(quantidade));
        }
        
        return valorTotal;
    }
    
    public int verificaQuantidade(int quantidade) {
    	if (quantidade <= 0 || Integer.toString(quantidade) == null){
    		this.quantidade = quantidade = 1;
    		return quantidade;
    	} else {
    		this.quantidade = quantidade;
    		return quantidade;
    	}
    }
    
    public Date formataData(String dataCadastro) throws ParseException {
    	if (dataCadastro.isEmpty()) {
    		java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
    		return data; 
    	} else {
    		SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
    		java.util.Date dataf = parser.parse(dataCadastro);             
            java.sql.Date dataSql = new java.sql.Date(dataf.getTime());
    		return dataSql;
    	}	
    }
}
