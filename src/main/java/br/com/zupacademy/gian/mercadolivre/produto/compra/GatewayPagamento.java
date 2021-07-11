package br.com.zupacademy.gian.mercadolivre.produto.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

	PAYPAL {
		@Override
		String criarUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	},
	PAGSEGURO {
		@Override
		String criarUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPagseguro;
		}
	};
	
	abstract String criarUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);
}
