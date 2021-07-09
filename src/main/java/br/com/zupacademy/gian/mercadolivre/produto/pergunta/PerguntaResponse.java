package br.com.zupacademy.gian.mercadolivre.produto.pergunta;

import java.time.LocalDateTime;

public class PerguntaResponse {

	private String titulo;
	private LocalDateTime dataCriacao;

	public PerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.dataCriacao = pergunta.getDataCriacao();
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
}
