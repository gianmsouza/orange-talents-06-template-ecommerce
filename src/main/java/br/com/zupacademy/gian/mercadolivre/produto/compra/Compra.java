package br.com.zupacademy.gian.mercadolivre.produto.compra;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status = Status.INICIADO;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gatewayPagamento;
	
	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	private Integer quantidade;

	@NotNull
	@ManyToOne
	private Usuario comprador;

	@NotNull
	private BigDecimal valorProdutoNaCompra;
	
	@Deprecated
	public Compra() {
	}

	public Compra(GatewayPagamento gatewayPagamento, Produto produto, Integer quantidade, Usuario comprador,
			BigDecimal valorProdutoNaCompra) {
		this.gatewayPagamento = gatewayPagamento;
		this.produto = produto;
		this.quantidade = quantidade;
		this.comprador = comprador;
		this.valorProdutoNaCompra = valorProdutoNaCompra;
	}

	public Long getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public BigDecimal getValorProdutoNaCompra() {
		return valorProdutoNaCompra;
	}
}
