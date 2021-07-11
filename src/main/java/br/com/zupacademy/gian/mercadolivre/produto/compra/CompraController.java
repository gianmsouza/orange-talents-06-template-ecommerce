package br.com.zupacademy.gian.mercadolivre.produto.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.mercadolivre.compartilhado.EnviaEmailFake;
import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;
import br.com.zupacademy.gian.mercadolivre.produto.Produto;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	private EnviaEmailFake enviaEmail;
	
	@PersistenceContext
	EntityManager entityManager;
	
	public CompraController(EnviaEmailFake enviaEmail) {
		this.enviaEmail = enviaEmail;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> fecharCompra(@Valid @RequestBody NovaCompraRequest request,
			@AuthenticationPrincipal UsuarioLogado comprador, 
			UriComponentsBuilder uriComponentsBuilder) throws BindException {	
		
		Produto produto = entityManager.find(Produto.class, request.getIdProduto());	
		
		boolean ajustouEstoque = produto.ajustarEstoque(request.getQuantidade());		
		
		if (!ajustouEstoque) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("produto", "produto sem saldo suficiente"));
		}		
		
		Compra compra = request.toModel(produto, comprador);		
		entityManager.persist(compra);
		
		enviaEmail.enviarEmailNovaCompra(compra);
		
		return ResponseEntity.status(302).body(
				compra.getGatewayPagamento().criarUrlRetorno(compra, uriComponentsBuilder));
	}
}
