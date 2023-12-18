package br.insper.desagil.backend.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Usuario;
import br.edu.insper.desagil.backend.endpoint.UsuarioEndpoint;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.rest.server.Args;

class UsuarioEndpointTeste {
	private static Firebase firebase;
	private UsuarioEndpoint usuarioEndpoint;
	private Args args;
	private Usuario usuario;
	private static String login;

	void setLogin(String newlogin) {
    	login = newlogin;
    }

	@BeforeAll
    static void setUpClass() {
        Factory factory = new Factory("test");
        firebase = factory.createFirebase();
        firebase.connect();
    }

    @AfterAll
    static void tearDownClass() {
        firebase.disconnect();
        firebase.remove();
    }

	@BeforeEach
    void setUp() {
		usuarioEndpoint = new UsuarioEndpoint();
		args = new Args();
		args.put("login", "login");
		args.put("senha", "teste");
		args.put("admin", "false");
		usuario = new Usuario();
    }

	@Test
	void testaPost() {
		Usuario resultado = (Usuario) usuarioEndpoint.post(args, usuario);
		setLogin(resultado.getLogin());
		assertNotNull(login);
	}
	
	@Test
	void testaGet() {
		System.out.println("login: " + login);
		Usuario resultado = usuarioEndpoint.get(args);
		assertNotNull(resultado.getLogin());
		assertFalse(resultado.isAdmin());
	}

	@Test
	void testaPut() {
		usuario = usuarioEndpoint.get(args);
		args.put("comando", "setAdmin");
		args.put("admin", "true");
		Usuario resultado = (Usuario) usuarioEndpoint.put(args, usuario);
		assertTrue(resultado.isAdmin());
	}

	@Test
	void testaDelete() {
		Usuario resultado = (Usuario) usuarioEndpoint.delete(args);
		assertNull(resultado);
	}

}
