package br.com.zupacademy.gian.mercadolivre.compartilhado.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsMapper {

	UserDetails map(Object shouldBeASystemUser);
}
