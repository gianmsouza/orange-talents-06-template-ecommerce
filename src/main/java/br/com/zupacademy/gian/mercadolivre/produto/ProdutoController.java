package br.com.zupacademy.gian.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {	
	
	@Autowired
	MontaProdutoDetalhes montaProdutoDetalhes;
	
	@PersistenceContext
	EntityManager entityManager;	

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado user) {
		if (request.temCaracteristicasIguais()) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("caracteristicas.nome", "há nomes de caracteristicas idênticos"));
		}

		Produto produto = request.toModel(entityManager, user);
		entityManager.persist(produto);

		Set<CaracteristicasProduto> listaCaracteristicas = request.getListaCaracteristicas(produto);
		
		for (CaracteristicasProduto c : listaCaracteristicas) {
			entityManager.persist(c);
		}

		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "{id}/detalhes")
	public ResponseEntity<?> montarPaginaDetalheProduto(@PathVariable ("id") Long id) {
		Produto produto = entityManager.find(Produto.class, id);
		
		if (produto == null) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("produto", "produto informado não existe"));
		}
		
		return ResponseEntity.ok(new ProdutoDetalhesResponse(produto, entityManager, montaProdutoDetalhes));
	}	
}
