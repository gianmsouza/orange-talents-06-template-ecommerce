package br.com.zupacademy.gian.mercadolivre.produto.opiniao;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;
import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.produto.ProdutoRepository;

@RestController
public class OpiniaoProdutoController {

	private OpiniaoProdutoRepository opiniaoRepository;
	private ProdutoRepository produtoRepository;
	
	public OpiniaoProdutoController(OpiniaoProdutoRepository opiniaoRepository,
			ProdutoRepository produtoRepository) {
		this.opiniaoRepository = opiniaoRepository;
		this.produtoRepository = produtoRepository;
	}

	@RequestMapping(value = "/produtos/{id}/opiniao")
	public ResponseEntity<?> cadastrarOpiniaoProduto(@PathVariable ("id") Long id, 
			@RequestBody @Valid NovaOpiniaoProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado user) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("produto", "produto informado não existe"));
		}
		
		OpiniaoProduto opiniaoProduto = request.toModel(user.get(), produto.get());
		opiniaoRepository.save(opiniaoProduto);
		
		return ResponseEntity.ok().build();		
	}
}
