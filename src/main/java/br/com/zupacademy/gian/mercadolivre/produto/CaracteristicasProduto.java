package br.com.zupacademy.gian.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicasProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public CaracteristicasProduto() {
	}
	
	public CaracteristicasProduto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public CaracteristicasProduto(String nome, String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicasProduto other = (CaracteristicasProduto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
