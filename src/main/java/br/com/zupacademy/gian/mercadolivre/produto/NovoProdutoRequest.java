package br.com.zupacademy.gian.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.gian.mercadolivre.categoria.Categoria;
import br.com.zupacademy.gian.mercadolivre.compartilhado.ExistsId;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;

	@NotNull
	@DecimalMin(value = "0.01", inclusive = true)
	private BigDecimal valor;

	@NotNull
	@Min(value = 1)
	private Integer quantidade;

	@NotNull
	@Size(min = 3)
	@Valid
	private Set<CaracteristicasProdutoRequest> caracteristicas = new HashSet<>();

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@ExistsId(domainClass = Categoria.class, fieldName = "id", message = "categoria não encontrada")
	private Long idCategoria;
	
	public Produto toModel(EntityManager entityManager, UsuarioLogado user) {	
		
		Categoria categoria = entityManager.find(Categoria.class, idCategoria);
		
		//Optional<Categoria> categorias = categoriaRepository.findById(idCategoria);
		
		return new Produto(nome, valor, quantidade, categoria, user.get(), descricao);
	}	

	public NovoProdutoRequest(String nome, BigDecimal valor,
			Integer quantidade, Set<CaracteristicasProdutoRequest> caracteristicas,
			String descricao, Long idCategoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
	}
	
	public boolean temCaracteristicasIguais() {
		Set<String> nomesIguais = new HashSet<>();
		
		for (CaracteristicasProdutoRequest caracteristica : caracteristicas) {
			if (!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}
		return false;
	}
	
	public Set<CaracteristicasProduto> getListaCaracteristicas(Produto produto) {
		Set<CaracteristicasProduto> listaCaracteristicas = new HashSet<>();
		
		for (CaracteristicasProdutoRequest c : getCaracteristicas()) {
			CaracteristicasProduto caracteristica = new 
					CaracteristicasProduto(c.getNome(), c.getDescricao(), produto);	
			
			listaCaracteristicas.add(caracteristica);
		}	
		
		return listaCaracteristicas;
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

	public Set<CaracteristicasProdutoRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}
}
