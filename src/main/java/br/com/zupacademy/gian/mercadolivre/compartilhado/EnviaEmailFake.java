package br.com.zupacademy.gian.mercadolivre.compartilhado;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.mercadolivre.produto.compra.Compra;
import br.com.zupacademy.gian.mercadolivre.produto.pergunta.Pergunta;

@Component
public class EnviaEmailFake {
	
	//aqui enviar email de verdade :D
	//se der tempo na semana, volto para tentar implementar

	public void enviarEmailNovaPergunta(Pergunta pergunta) {
		System.out.println("Olá, você recebeu uma nova mensagem :D");
		System.out.println("Quem perguntou: " + pergunta.getUsuario().getLogin());
		System.out.println("Pergunta: " + pergunta.getTitulo());
		System.out.println("Sobre o produto: " + pergunta.getProduto().getNome());
		System.out.println("Fim da mensagem!");
	}
	
	public void enviarEmailNovaCompra(Compra compra) {
		System.out.println("Olá, você teve uma venda :D");
		System.out.println("Quem comprou: " + compra.getComprador().getLogin());
		System.out.println("Produto comprado: " + compra.getProduto().getNome());
		System.out.println("Quantidade: " + compra.getQuantidade());
		System.out.println("Valor Unitário: " + compra.getValorProdutoNaCompra());
		System.out.println("Fim da mensagem!");
	}

	public void enviarEmailPagamentoSucesso(Compra compra) {
		System.out.println("Olá, seu pagamento foi aprovado :D");
		System.out.println("Produto comprado: " + compra.getProduto().getNome());
		System.out.println("Quantidade: " + compra.getQuantidade());
		System.out.println("Valor Unitário: " + compra.getValorProdutoNaCompra());
		System.out.println("Fim da mensagem!");	
	}

	public void enviarEmailPagamentoErro(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
		String urlRetorno = compra.getGatewayPagamento().criarUrlRetorno(compra, uriComponentsBuilder);
		System.out.println("Olá, seu pagamento foi reprovado D:");
		System.out.println("Faça uma nova tentativa de pagamento em: " + urlRetorno);
	}
}
