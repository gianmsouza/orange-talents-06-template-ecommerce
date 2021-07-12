package br.com.zupacademy.gian.mercadolivre.compartilhado;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusRetornoPagamentoValidator implements ConstraintValidator<StatusRetornoPagamento, Object>  {
	
	private List<String> listaRetornos = Arrays.asList("0", "1", "SUCESSO", "ERRO");
	
	@Override
	public void initialize(StatusRetornoPagamento params) {
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {	
		if (listaRetornos.contains(value)) {
			return true;
		}
		return false;
	}
}
