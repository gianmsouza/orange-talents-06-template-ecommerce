package br.com.zupacademy.gian.mercadolivre.produto;

public class CaracteristicasProdutoResponse {

	private String nome;
	private String descricao;

	public CaracteristicasProdutoResponse(CaracteristicasProduto caracteristicasProduto) {
		this.nome = caracteristicasProduto.getNome();
		this.descricao = caracteristicasProduto.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
