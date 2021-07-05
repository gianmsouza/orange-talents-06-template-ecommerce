package br.com.zupacademy.gian.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.gian.mercadolivre.compartilhado.UniqueValue;

public class NovoUsuarioRequest {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "e-mail já cadastrado")
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String senha;	
	
	public Usuario toModel() {
		return new Usuario(login, senha);
	} 
	
	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
}
