package br.com.zupacademy.gian.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private BigDecimal valor;
	private Integer quantidade;

	@OneToMany(mappedBy = "produto")
	private Set<CaracteristicasProduto> caracteristicas;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public Produto() {}	
	
	public Produto(String nome, BigDecimal valor, Integer quantidade) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, 
			Set<CaracteristicasProduto> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Set<CaracteristicasProduto> getCaracteristicas() {
		return caracteristicas;
	}
	
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
}
