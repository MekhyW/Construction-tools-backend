package br.edu.insper.desagil.backend.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FerramentaTeste {
	private Ferramenta ferramenta;
	
	@BeforeEach
	private void setUp() {
		ferramenta = new Ferramenta();
	}
	
	@Test
	void testaNome() {
		ferramenta.setNome("novo");
		assertEquals("novo", ferramenta.getNome());
	}

	@Test
	void testaAdicionaItem() {
		Item item = new Item();
		ferramenta.adicionaItem(item);
		assertEquals(1, ferramenta.getItems().size());
	}

	@Test
	void testaRemoveItem() {
		Item item = new Item();
		ferramenta.adicionaItem(item);
		ferramenta.removeItem(item);
		assertEquals(0, ferramenta.getItems().size());
	}
}
