package br.com.zupacademy.gian.mercadolivre.produto;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private CaracteristicasProdutoRepository caracteristicasProdutoRepository;
	private CategoriaRepository categoriaRepository;

	public ProdutoController(ProdutoRepository produtoRepository,
			CaracteristicasProdutoRepository caracteristicasProdutoRepository,
			CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.caracteristicasProdutoRepository = caracteristicasProdutoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest request,
			@AuthenticationPrincipal UsuarioLogado user) {
		if (request.temCaracteristicasIguais()) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("caracteristicas.nome", "há nomes de caracteristicas idênticos"));
		}

		Produto produto = request.toModel(categoriaRepository, user);
		produtoRepository.save(produto);

		Set<CaracteristicasProduto> listaCaracteristicas = request.getListaCaracteristicas(produto);
		caracteristicasProdutoRepository.saveAll(listaCaracteristicas);

		return ResponseEntity.ok().build();
	}
}
