package br.com.java.model;

import java.util.Date;

public class PagamentoBEAN {
	
	private int id;
	private TipoPagamentoBEAN tipoPagamento;
	private int parcelas;
	private float valorParcelas;
	private float valorTotal;
	private Date dataRegistro;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoPagamentoBEAN getTipoPagamento() {
		return tipoPagamento;
	}
	public void setTipoPagamento(TipoPagamentoBEAN tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public int getParcelas() {
		return parcelas;
	}
	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}
	public float getValorParcelas() {
		return valorParcelas;
	}
	public void setValorParcelas(float valorParcelas) {
		this.valorParcelas = valorParcelas;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
}
