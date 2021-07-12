package br.com.zupacademy.gian.mercadolivre.produto.pagamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.produto.compra.Compra;

@RestController
public class RetornoPagamentoController {
	
	private RetornoPagamentoRepository retornoPagamentoRepository;	
	private EventosPagamento eventosPagamento;
	
	public RetornoPagamentoController(RetornoPagamentoRepository retornoPagamentoRepository,
			EventosPagamento eventosPagamento) {
		this.retornoPagamentoRepository = retornoPagamentoRepository;
		this.eventosPagamento = eventosPagamento;
	}
	
	@PersistenceContext
	EntityManager entityManager;

	@PostMapping(value = "/retorno-pagamento/{id}")
	@Transactional
	public ResponseEntity<?> processarRetornoPagamento(@PathVariable ("id") Long id,
			@RequestBody @Valid NovoRetornoPagamentoRequest request,
			UriComponentsBuilder uriComponentsBuilder) {
		
		Compra compra = entityManager.find(Compra.class, id);
		
		if (compra == null) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("compra", "compra informada não existe"));
		}
		
		List<RetornoPagamento> pagamentos = retornoPagamentoRepository.findByCompraIdAndStatus(compra.getId());
		
		if (pagamentos.size() > 0) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("compra", "compra informada já está paga"));
		}
		
		RetornoPagamento retornoPagamento = request.toModel(compra);		
		entityManager.persist(retornoPagamento);
		
		eventosPagamento.processar(compra, retornoPagamento.getStatus(), uriComponentsBuilder);
		
		return ResponseEntity.ok(retornoPagamento.getStatus());	
	}	
}
