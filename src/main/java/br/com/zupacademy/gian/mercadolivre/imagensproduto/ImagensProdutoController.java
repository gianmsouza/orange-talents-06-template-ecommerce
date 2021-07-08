package br.com.zupacademy.gian.mercadolivre.imagensproduto;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;
import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.produto.ProdutoRepository;

@RestController
public class ImagensProdutoController {

	private ProdutoRepository produtoRepository;
	private UploaderFake uploaderFake;
	private ImagensProdutoRepository imagensProdutoRepository;

	public ImagensProdutoController(UploaderFake uploaderFake, ProdutoRepository produtoRepository,
			ImagensProdutoRepository imagensProdutoRepository) {
		this.uploaderFake = uploaderFake;
		this.produtoRepository = produtoRepository;
		this.imagensProdutoRepository = imagensProdutoRepository;
	}

	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	public ResponseEntity<?> inserirImagemProduto(@PathVariable("id") Long id, @Valid NovasImagensRequest request,
			@AuthenticationPrincipal UsuarioLogado user) {

		Optional<Produto> produto = produtoRepository.findByIdAndUsuarioId(id, user.get().getId());

		if (produto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		Set<String> links = uploaderFake.enviar(request.getImagens());

		Set<ImagensProduto> imagensProduto = request.toModel(produto.get(), links);
		imagensProdutoRepository.saveAll(imagensProduto);
		
		return ResponseEntity.ok().build();
	}
}
