package br.com.java.model;

import java.util.Date;

public class OrdemServicoBEAN {
	
	private int id;
	private String status;
	private ClienteBEAN cliente;
	private String descricaoProblema;
	private UsuarioBEAN usuario;
	private String descricaoSolucao;
	private String[] pecas_usadas;
	private PagamentoBEAN pagamento;
	private Date dataRegistro;
	
	public OrdemServicoBEAN() {
		
		this.cliente = new ClienteBEAN();
		this.usuario = new UsuarioBEAN();
		this.pagamento = new PagamentoBEAN();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ClienteBEAN getCliente() {
		return cliente;
	}
	public void setCliente(ClienteBEAN cliente) {
		this.cliente = cliente;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}
	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}
	public UsuarioBEAN getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBEAN usuario) {
		this.usuario = usuario;
	}
	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}
	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}
	public String[] getPecas_usadas() {
		return pecas_usadas;
	}
	public void setPecas_usadas(String[] pecas_usadas) {
		this.pecas_usadas = pecas_usadas;
	}
	public PagamentoBEAN getPagamento() {
		return pagamento;
	}
	public void setPagamento(PagamentoBEAN pagamento) {
		this.pagamento = pagamento;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String pecas_usadas_toString() {
		String pecasUsadas = ""; 
		for(int i = 0; i < this.pecas_usadas.length; i++) {
			if(i > 0) {
				pecasUsadas += ",";
			}
			pecasUsadas += this.pecas_usadas[i];
		}
		
		return pecasUsadas;
	}
	
}
