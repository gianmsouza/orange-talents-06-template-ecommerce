package br.com.zupacademy.gian.mercadolivre.produto.pagamento;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.zupacademy.gian.mercadolivre.produto.compra.Compra;

@Entity
public class RetornoPagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idPagamento;
	
	private String status;
	
	@ManyToOne
	private Compra compra;
	
	private LocalDateTime dataProcessamento;
	
	@Deprecated
	public RetornoPagamento() {
	}	

	public RetornoPagamento(String status, Compra compra, Long idPagamento) {
		this.status = status;
		this.compra = compra;
		this.idPagamento = idPagamento;
		this.dataProcessamento = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public Long getIdPagamento() {
		return idPagamento;
	}

	public String getStatus() {
		return status;
	}

	public Compra getCompra() {
		return compra;
	}

	public LocalDateTime getDataProcessamento() {
		return dataProcessamento;
	}	
}
