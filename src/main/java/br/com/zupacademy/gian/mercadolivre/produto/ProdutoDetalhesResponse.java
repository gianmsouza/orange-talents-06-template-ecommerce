package br.com.zupacademy.gian.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.zupacademy.gian.mercadolivre.produto.opiniao.OpiniaoProdutoResponse;
import br.com.zupacademy.gian.mercadolivre.produto.pergunta.PerguntaResponse;

public class ProdutoDetalhesResponse {

	private Set<String> linksImagens = new HashSet<>();
	private String nome;
	private BigDecimal valor;
	private Set<CaracteristicasProdutoResponse> caracteristicas = new HashSet<>();
	private String descricao;
	private String categoria;
	private Double mediaNotaProduto;
	private Long totalNotasProduto;
	private List<OpiniaoProdutoResponse> opinioes = new ArrayList<>();
	private List<PerguntaResponse> perguntas = new ArrayList<>();

	public ProdutoDetalhesResponse(Produto produto, EntityManager manager, MontaProdutoDetalhes montaProdutoDetalhes) {
		this.linksImagens = montaProdutoDetalhes.listaLinksImagens(produto.getId(), manager);
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria().getNome();
		this.caracteristicas = montaProdutoDetalhes.listaCaracteristicasProduto(produto.getId(), manager);
		this.mediaNotaProduto = montaProdutoDetalhes.mediaNotaProduto(produto.getId(), manager);
		this.totalNotasProduto = montaProdutoDetalhes.contarTotalDeNotasProduto(produto.getId(), manager);
		this.opinioes = montaProdutoDetalhes.listaOpinioesProduto(produto.getId(), manager);
		this.perguntas = montaProdutoDetalhes.listaPerguntasProduto(produto.getId(), manager);
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<CaracteristicasProdutoResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotaProduto() {
		return mediaNotaProduto;
	}

	public Long getTotalNotasProduto() {
		return totalNotasProduto;
	}

	public List<OpiniaoProdutoResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaResponse> getPerguntas() {
		return perguntas;
	}
	
	public String getCategoria() {
		return categoria;
	}
}
