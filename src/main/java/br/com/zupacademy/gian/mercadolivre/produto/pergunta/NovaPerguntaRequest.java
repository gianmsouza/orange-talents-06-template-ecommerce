package br.com.zupacademy.gian.mercadolivre.produto.pergunta;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.gian.mercadolivre.produto.Produto;
import br.com.zupacademy.gian.mercadolivre.usuario.Usuario;

public class NovaPerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	public NovaPerguntaRequest() {
	}
	
	public NovaPerguntaRequest(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {
		return new Pergunta(titulo, usuario, produto);
	}
}
