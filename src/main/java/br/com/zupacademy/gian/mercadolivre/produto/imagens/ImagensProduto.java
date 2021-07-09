package br.com.zupacademy.gian.mercadolivre.produto.imagens;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;

@Entity
public class ImagensProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String link;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public ImagensProduto() {
	}
	
	public ImagensProduto(String link, Produto produto) {
		this.link = link;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}
}
