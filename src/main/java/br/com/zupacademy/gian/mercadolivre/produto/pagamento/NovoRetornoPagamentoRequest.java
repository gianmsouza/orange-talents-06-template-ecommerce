package br.com.zupacademy.gian.mercadolivre.produto.pagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.gian.mercadolivre.compartilhado.StatusRetornoPagamento;
import br.com.zupacademy.gian.mercadolivre.produto.compra.Compra;

public class NovoRetornoPagamentoRequest {
	
	@NotNull
	@Positive
	private Long idPagamento;	
	
	@NotBlank
	@StatusRetornoPagamento(domainClass = StatusRetornoPagamento.class, fieldName = "status", message = "status não aceito")
	private String status;

	public NovoRetornoPagamentoRequest(Long idPagamento, String status) {
		this.idPagamento = idPagamento;
		this.status = status;
	}
	
	public RetornoPagamento toModel(Compra compra) {
		return new RetornoPagamento(this.status, compra, this.idPagamento);
	}	

	public Long getIdPagamento() {
		return idPagamento;
	}

	public String getStatus() {
		return status;
	}
}
