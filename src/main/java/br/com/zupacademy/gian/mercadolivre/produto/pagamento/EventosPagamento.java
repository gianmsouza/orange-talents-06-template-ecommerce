package br.com.zupacademy.gian.mercadolivre.produto.pagamento;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.mercadolivre.compartilhado.EnviaEmailFake;
import br.com.zupacademy.gian.mercadolivre.produto.compra.Compra;
import br.com.zupacademy.gian.mercadolivre.produto.pagamento.outrossistemas.NotaFiscal;
import br.com.zupacademy.gian.mercadolivre.produto.pagamento.outrossistemas.RankingVendedores;

@Service
public class EventosPagamento {
	
	private NotaFiscal notaFiscal;
	private RankingVendedores rankingVendedores;
	private EnviaEmailFake emailFake;

	public EventosPagamento(NotaFiscal notaFiscal, RankingVendedores rankingVendedores, EnviaEmailFake emailFake) {
		this.notaFiscal = notaFiscal;
		this.rankingVendedores = rankingVendedores;
		this.emailFake = emailFake;
	}
	
	public void processar(Compra compra, String status, UriComponentsBuilder uriComponentsBuilder) {
		if (status.equals("1") || status.equals("SUCESSO")) {
			notaFiscal.enviarNotaFiscal(compra.getId(), compra.getComprador().getId());
			rankingVendedores.enviarVendaParaRanking(compra.getId(), compra.getProduto().getUsuario().getId());
			emailFake.enviarEmailPagamentoSucesso(compra);
		} else {
			emailFake.enviarEmailPagamentoErro(compra, uriComponentsBuilder);
		}
	}
}
