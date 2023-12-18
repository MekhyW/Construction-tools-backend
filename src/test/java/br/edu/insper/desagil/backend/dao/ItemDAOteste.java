package br.edu.insper.desagil.backend.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Item;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.dao.Selection;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDAOteste {
    private static Firebase firebase;

    private ItemDAO itemDao;
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
        itemDao = new ItemDAO();
        Selection selection = itemDao.select();
        itemDao.delete(selection);
    }
    
    @Test
    void testaCria() {
    	Item item = new Item();
        itemDao.create(item);
        key = item.getId();
        assertNotNull(key);
    }

    @Test
    void testaLe() {
    	Item item = new Item();
        itemDao.create(item);
        String id = item.getId();
        assertTrue(itemDao.retrieve(id).getDisponivel());
        assertEquals(0, itemDao.retrieve(id).getNumObservacoes());
        assertEquals(0, itemDao.retrieve(id).getRetiradas());
    }
    
    @Test
    void testaAtualiza() {
    	Item item = new Item();
        itemDao.create(item);
        String id = item.getId();
        item.alocaItem();
        itemDao.update(item);
        assertFalse(itemDao.retrieve(id).getDisponivel());
    }
    
    @Test
    void testaDeleta() {
    	Item item = new Item();
        itemDao.create(item);
        String id = item.getId();
        itemDao.delete(id);
        assertNull(itemDao.retrieve(id));
    }
}
