package br.edu.insper.desagil.backend.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Ferramenta;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.dao.Selection;

public class FerramentaDAOteste {
    private static Firebase firebase;

    private FerramentaDAO ferramentaDao;
    private String key;

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
        ferramentaDao = new FerramentaDAO();
        Selection selection = ferramentaDao.select();
        ferramentaDao.delete(selection);
    }
    
    @Test
    void testaCria() {
    	Ferramenta ferramenta = new Ferramenta();
        ferramentaDao.create(ferramenta);
        key = ferramenta.getId();
        assertNotNull(key);
    }

    @Test
    void testaLe() {
    	Ferramenta ferramenta = new Ferramenta();
        ferramentaDao.create(ferramenta);
        String id = ferramenta.getId();
        assertEquals(0, ferramentaDao.retrieve(id).getNome().length());
        assertEquals(0, ferramentaDao.retrieve(id).getItems().size());
    }
    
    @Test
    void testaAtualiza() {
    	Ferramenta ferramenta = new Ferramenta();
        ferramentaDao.create(ferramenta);
        String id = ferramenta.getId();
        ferramenta.setNome("novo nome");
        ferramentaDao.update(ferramenta);
        assertEquals("novo nome", ferramentaDao.retrieve(id).getNome());
    }
    
    @Test
    void testaDeleta() {
    	Ferramenta ferramenta = new Ferramenta();
        ferramentaDao.create(ferramenta);
        String id = ferramenta.getId();
        ferramentaDao.delete(id);
        assertNull(ferramentaDao.retrieve(id));
    }
}

