package br.com.zupacademy.gian.mercadolivre.imagensproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;

@Entity
public class ImagensProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String link;
	
	@ManyToOne
	private Produto produto;
	
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
