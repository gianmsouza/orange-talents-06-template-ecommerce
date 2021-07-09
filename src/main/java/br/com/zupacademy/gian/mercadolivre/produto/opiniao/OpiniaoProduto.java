package br.com.zupacademy.gian.mercadolivre.produto.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer nota;

	@NotNull
	private String titulo;

	@NotNull
	private String descricao;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public OpiniaoProduto() {
	}

	public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
}
