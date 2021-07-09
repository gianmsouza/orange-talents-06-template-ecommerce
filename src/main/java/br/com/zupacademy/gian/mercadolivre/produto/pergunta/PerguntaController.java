package br.com.zupacademy.gian.mercadolivre.produto.pergunta;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.mercadolivre.compartilhado.UsuarioLogado;
import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.produto.ProdutoRepository;

@RestController
public class PerguntaController {
	
	private PerguntaRepository perguntaRepository;
	private ProdutoRepository produtoRepository;
	private EnviaEmailFake emailFake;
	
	public PerguntaController(PerguntaRepository perguntaRepository, ProdutoRepository produtoRepository, EnviaEmailFake emailFake) {
		this.perguntaRepository = perguntaRepository;
		this.produtoRepository = produtoRepository;
		this.emailFake = emailFake;
	}

	@PostMapping(value = "/produtos/{id}/perguntas")
	public ResponseEntity<?> cadastrarPergunta(@PathVariable ("id") Long id, 
			@RequestBody @Valid NovaPerguntaRequest request,
			@AuthenticationPrincipal UsuarioLogado user) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isEmpty()) {
			return ResponseEntity.badRequest()
					.body(new ErroDeFormularioDto("produto", "produto informado não existe"));
		}
		
		Pergunta pergunta = request.toModel(produto.get(), user.get());
		
		emailFake.enviarEmail(pergunta);
		
		perguntaRepository.save(pergunta);
		
		return ResponseEntity.ok().build();	
	}	
}
