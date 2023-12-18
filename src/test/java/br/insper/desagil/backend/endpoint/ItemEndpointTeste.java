package br.insper.desagil.backend.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Item;
import br.edu.insper.desagil.backend.endpoint.ItemEndpoint;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.rest.server.Args;

class ItemEndpointTeste {
	private static Firebase firebase;
	private ItemEndpoint itemEndpoint;
	private Args args;
	private Item item;
    private static String key;
	
    void setKey(String newkey) {
    	key = newkey;
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
		itemEndpoint = new ItemEndpoint();
		args = new Args();
        args.put("key", key);
		item = new Item();
    }
	
	@Test
	void testaPost() {
		Item resultado = (Item) itemEndpoint.post(args, item);
		setKey(resultado.getId());
		assertNotNull(key);
		assertTrue(resultado.getDisponivel());
	}
	
	@Test
	void testaGet() {
		Item resultado = (Item) itemEndpoint.get(args);
		assertNotNull(resultado.getId());
		assertTrue(resultado.getDisponivel());
	}

	@Test
	void testaPut() {
		item = (Item) itemEndpoint.get(args);
		args.put("comando", "alocaItem");
		Item resultado = (Item) itemEndpoint.put(args, item);
		assertFalse(resultado.getDisponivel());
	}

	@Test
	void testaDelete() {
		Item resultado = (Item) itemEndpoint.delete(args);
		assertNull(resultado);
	}

}
