package br.com.zupacademy.gian.mercadolivre.produto.opiniao;

public class OpiniaoProdutoResponse {

	private String titulo;
	private String descricao;
	private Integer nota;

	public OpiniaoProdutoResponse(OpiniaoProduto opiniaoProduto) {
		this.titulo = opiniaoProduto.getTitulo();
		this.descricao = opiniaoProduto.getDescricao();
		this.nota = opiniaoProduto.getNota();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}
}
