package br.com.zupacademy.gian.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public CaracteristicasProdutoRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}	
}
