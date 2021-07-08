package br.com.zupacademy.gian.mercadolivre.produto.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EmailFake {
	
	//aqui enviar email de verdade :D
	//se der tempo na semana, volto para tentar implementar

	public void enviarEmail(Pergunta pergunta) {
		System.out.println("Olá, você recebeu uma nota pergunta: ");
		System.out.println("Quem perguntou: " + pergunta.getUsuario().getLogin());
		System.out.println("Pergunta: " + pergunta.getTitulo());
		System.out.println("Sobre o produto: " + pergunta.getProduto().getNome());
		System.out.println("Fim da mensagem!");
	}
}
