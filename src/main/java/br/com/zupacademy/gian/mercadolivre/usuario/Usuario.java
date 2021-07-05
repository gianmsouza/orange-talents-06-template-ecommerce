package br.com.zupacademy.gian.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime criadoEm = LocalDateTime.now();
	
	@Column(unique = true)
	@NotNull
	private String login;

	@NotNull
	@Length(min = 6)
	private String senha;
	
	@Deprecated
	public Usuario() {
	}

	public Usuario(String login, String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}
}
