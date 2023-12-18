package br.edu.insper.desagil.backend.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.edu.insper.desagil.backend.Factory;
import br.edu.insper.desagil.backend.core.Usuario;
import br.pro.hashi.nfp.dao.Firebase;
import br.pro.hashi.nfp.dao.Selection;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOteste {
    private static Firebase firebase;

    private UsuarioDAO usuarioDao;
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
        usuarioDao = new UsuarioDAO();
        Selection selection = usuarioDao.select();
        usuarioDao.delete(selection);
    }
    
    @Test
    void testaCria() {
    	Usuario usuario = new Usuario();
        usuarioDao.create(usuario);
        key = usuario.getLogin();
        assertNotNull(key);
    }

    @Test
    void testaLe() {
    	Usuario usuario = new Usuario();
        usuarioDao.create(usuario);
        String id = usuario.getId();
        assertEquals("default", usuarioDao.retrieve(id).getLogin());
        assertEquals(0, usuarioDao.retrieve(id).getSenha().length());
        assertFalse(usuarioDao.retrieve(id).isAdmin());
    }
    
    @Test
    void testaAtualiza() {
    	Usuario usuario = new Usuario();
        usuarioDao.create(usuario);
        String id = usuario.getId();
        usuario.setLogin("teste@teste.com.br");
        usuarioDao.update(usuario);
        assertEquals("teste@teste.com.br", usuarioDao.retrieve(id).getLogin());
    }
    
    @Test
    void testaDeleta() {
    	Usuario usuario = new Usuario();
        usuarioDao.create(usuario);
        String id = usuario.getId();
        usuarioDao.delete(id);
        assertNull(usuarioDao.retrieve(id));
    }
}
