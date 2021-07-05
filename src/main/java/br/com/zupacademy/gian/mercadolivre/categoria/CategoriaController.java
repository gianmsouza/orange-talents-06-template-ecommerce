package br.com.zupacademy.gian.mercadolivre.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.mercadolivre.compartilhado.ErroDeFormularioDto;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private CategoriaRepository categoriaRepository;
	
	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(categoriaRepository);		
				
		if (request.getIdCategoriaMae() != null && !request.isCategoriaMaeEncontrada()) {
			return ResponseEntity.badRequest().
					body(new ErroDeFormularioDto("idCategoriaMae", "id não encontrado"));
		}
		
		categoriaRepository.save(categoria);
		
		return ResponseEntity.ok().build();		
	}
}
