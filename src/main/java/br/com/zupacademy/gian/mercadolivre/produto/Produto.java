package br.com.zupacademy.gian.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gian.mercadolivre.categoria.Categoria;
import br.com.zupacademy.gian.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private BigDecimal valor;
	private Integer quantidade;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	private String descricao;

	@OneToMany(mappedBy = "produto")
	private Set<CaracteristicasProduto> caracteristicas;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@Deprecated
	public Produto() {}	
	
	public Produto(String nome, BigDecimal valor, 
			Integer quantidade, Categoria categoria, 
			Usuario usuario, String descricao) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.categoria = categoria;
		this.usuario = usuario;
		this.descricao = descricao;
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
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public String getDescricao() {
		return descricao;
	}
}
