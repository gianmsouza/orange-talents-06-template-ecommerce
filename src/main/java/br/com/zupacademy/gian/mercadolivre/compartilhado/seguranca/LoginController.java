package br.com.zupacademy.gian.mercadolivre.compartilhado.seguranca;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenManager tokenManager;
	
	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);

	@PostMapping
	public ResponseEntity<String> authenticate(@RequestBody @Valid LoginRequest loginInfo) {
	
		UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();

		try {
			Authentication authentication = authManager.authenticate(authenticationToken); 			
			String jwt = tokenManager.generateToken(authentication);
			
			return ResponseEntity.ok(jwt);
		
		} catch (AuthenticationException e) {
			//log.error("[Autenticacao] {}",e);
			return ResponseEntity.badRequest().build();
		}		
	}
}
