package br.com.zupacademy.gian.mercadolivre.categoria;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.gian.mercadolivre.compartilhado.UniqueValue;

public class NovaCategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "categoria já cadastrada")
	private String nome;	
	
	private Long idCategoriaMae;
	private boolean categoriaMaeEncontrada;

	public Categoria toModel(CategoriaRepository categoriaRepository) {			
		categoriaMaeEncontrada = false;		
		Categoria categoria = new Categoria(nome);		
		
		if (idCategoriaMae != null) {		
			
			Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
			
			if (categoriaMae.isPresent()) {
				categoria.setCategoriaMae(categoriaMae.get());
				categoriaMaeEncontrada = true;				
			}
		}
		
		return categoria;
	}	

	public NovaCategoriaRequest(String nome, Long idCategoriaMae, boolean categoriaMaeEncontrada) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
		this.categoriaMaeEncontrada = categoriaMaeEncontrada;
	}

	public String getNome() {
		return nome;
	}
	
	public boolean isCategoriaMaeEncontrada() {
		return categoriaMaeEncontrada;
	}
	
	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}
}
