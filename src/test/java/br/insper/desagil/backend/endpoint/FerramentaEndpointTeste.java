package br.insper.desagil.backend.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Ferramenta;
import br.edu.insper.desagil.backend.endpoint.FerramentaEndpoint;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.rest.server.Args;

class FerramentaEndpointTeste {
	private static Firebase firebase;
	private FerramentaEndpoint ferramentaEndpoint;
	private Args args;
	private Ferramenta ferramenta;
	
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
		ferramentaEndpoint = new FerramentaEndpoint();
		args = new Args();
		args.put("nome", "teste");
		ferramenta = new Ferramenta();
    }
	
	@Test
	void testaPost() {
		Ferramenta resultado = (Ferramenta) ferramentaEndpoint.post(args, ferramenta);
		assertNotNull(resultado.getId());
		assertEquals(resultado.getNome(), "teste");
	}
	
	@Test
	void testaGet() {
		Ferramenta resultado = (Ferramenta) ferramentaEndpoint.get(args);
		assertNotNull(resultado.getId());
		assertEquals(resultado.getNome(), "teste");
	}

	@Test
	void testaPut() {
		ferramenta = (Ferramenta) ferramentaEndpoint.get(args);
		args.put("comando", "setNome");
		args.put("nome", "teste2");
		Ferramenta resultado = (Ferramenta) ferramentaEndpoint.put(args, ferramenta);
		assertEquals(resultado.getNome(), "teste2");
	}

	@Test
	void testaDelete() {
		args.put("nome", "teste2");
		ferramenta = (Ferramenta) ferramentaEndpoint.get(args);
		args.put("id", ferramenta.getId());
		Ferramenta resultado = (Ferramenta) ferramentaEndpoint.delete(args);
		assertNull(resultado);
	}

}
