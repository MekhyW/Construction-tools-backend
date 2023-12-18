package br.edu.insper.desagil.backend.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTeste {
    private Item item;

    @BeforeEach
    private void setUp() {
        item = new Item();
    }

    @Test
    void testaAloca() {
        item.alocaItem();
        assertFalse(item.getDisponivel());
        assertEquals(1, item.getRetiradas());
    }

    @Test
    void testaDevolve() {
        item.devolveItem();
        assertTrue(item.getDisponivel());
    }

    @Test
    void testaAdicionaObservacao() {
        item.adicionaObservacao("teste");
        assertEquals(1, item.getObservacoes().size());
    }

    @Test
    void testaRemoveObservacao() {
        item.adicionaObservacao("teste");
        item.removeObservacao("teste");
        assertEquals(0, item.getObservacoes().size());
    }
}