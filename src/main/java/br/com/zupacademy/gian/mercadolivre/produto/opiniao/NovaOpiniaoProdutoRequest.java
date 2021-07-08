package br.com.zupacademy.gian.mercadolivre.produto.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.usuario.Usuario;

public class NovaOpiniaoProdutoRequest {

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public NovaOpiniaoProdutoRequest(Integer nota, String titulo, String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
		return new OpiniaoProduto(nota, titulo, descricao, usuario, produto);
	}	
}
