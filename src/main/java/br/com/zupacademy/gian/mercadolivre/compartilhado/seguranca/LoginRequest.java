package br.com.zupacademy.gian.mercadolivre.compartilhado.seguranca;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	@Email
	@NotBlank
	private String login;
	
	@NotBlank
	private String password;

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public UsernamePasswordAuthenticationToken build() {
		return new UsernamePasswordAuthenticationToken(this.login, this.password);
	}
}
