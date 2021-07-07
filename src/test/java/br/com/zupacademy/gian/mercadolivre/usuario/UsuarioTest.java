package br.com.zupacademy.gian.mercadolivre.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
public class UsuarioTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper jsonMapper;
	
	@Autowired
	EntityManager manager;	
	
	@Test
	public void deveCriarUmNovoUsuario() throws Exception {		
		mockMvc.perform(post("/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(new NovoUsuarioRequest("gian@hotmail.com", "senha123"))))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
		String sql = "from Usuario u where u.login = :login";
		
		TypedQuery<Usuario> query = manager.createQuery(sql, Usuario.class);
		query.setParameter("login", "gian@hotmail.com");
		
		List<Usuario> usuarios = query.getResultList();
		Usuario usuario = usuarios.get(0);
		
		assertTrue(usuarios.size() == 1);		
		assertEquals("gian@hotmail.com", usuario.getLogin());	
		assertNotNull(usuario.getCriadoEm());
	}

	private String toJson(NovoUsuarioRequest request) throws JsonProcessingException {
		return jsonMapper.writeValueAsString(request);
	}
}
