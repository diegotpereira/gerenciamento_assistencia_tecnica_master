package br.com.java.model;

public class PecaBEAN {
	
	private int id;
	private String nome;
	private float preco;
	private String descricao;
	private int quantidade;
	private TipoPecaBEAN tipoPeca;
	private FabricanteBEAN fabricante;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public TipoPecaBEAN getTipoPeca() {
		return tipoPeca;
	}
	public void setTipoPeca(TipoPecaBEAN tipoPeca) {
		this.tipoPeca = tipoPeca;
	}
	public FabricanteBEAN getFabricante() {
		return fabricante;
	}
	public void setFabricante(FabricanteBEAN fabricante) {
		this.fabricante = fabricante;
	}
}
