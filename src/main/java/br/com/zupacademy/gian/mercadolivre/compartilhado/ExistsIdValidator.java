package br.com.zupacademy.gian.mercadolivre.compartilhado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object>  {			
	
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(ExistsId params) {		
		klass = params.domainClass();
	}
		
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {			
		Object idPesquisado = manager.find(klass, value);
		
		if (idPesquisado != null) {			
			return true;
		}
		
		return false;
	}
}
