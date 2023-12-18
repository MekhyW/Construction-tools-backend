package br.edu.insper.desagil.backend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTeste {
    private Usuario usuario;

    @BeforeEach
    private void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testaLogin() {
    	usuario.setLogin("login");
        assertEquals("login", usuario.getLogin());
    }

    @Test
    void testaSenha() {
        usuario.setSenha("senha2");
        assertEquals("senha2", usuario.getSenha());
    }

    @Test
    void testaAdmin() {
        usuario.setAdmin(true);
        assertEquals(true, usuario.isAdmin());
    }
}