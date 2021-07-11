package br.com.zupacademy.gian.mercadolivre.produto.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ExistsId;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;
import br.com.zupacademy.gian.mercadolivre.produto.Produto;

public class NovaCompraRequest {

	@NotNull
	private GatewayPagamento gatewayPagamento;

	@ExistsId(domainClass = Produto.class, fieldName = "id", message = "Produto não encontrado")
	private Long idProduto;

	@Positive
	private Integer quantidade;

	public Compra toModel(Produto produto, UsuarioLogado comprador) {		
		return new Compra(gatewayPagamento, produto, quantidade, comprador.get(), produto.getValor());
	}

	public NovaCompraRequest(GatewayPagamento gatewayPagamento, Long idProduto, Integer quantidade) {
		this.gatewayPagamento = gatewayPagamento;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
}
