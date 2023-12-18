package br.edu.insper.desagil.backend.core;

import java.util.ArrayList;
import java.util.List;

import br.pro.hashi.nfp.dao.Autokey;

public class Ferramenta {
	@Autokey
    private String key;
    private String nome;
    private List<Item> items = new ArrayList<Item>();

    public Ferramenta() {
        this.nome = new String();
    }
    
    public String getId() {
    	return key;
    }

    public String getNome() {
        return nome;
    }

    public List<Item> getItems() {
        return items;
    }
    
    public void setId(String key) {
    	this.key = key;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionaItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }
}
