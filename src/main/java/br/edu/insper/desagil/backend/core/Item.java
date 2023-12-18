package br.edu.insper.desagil.backend.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.pro.hashi.nfp.dao.Autokey;

public class Item {
	@Autokey
    private String key;
	private Date dataInsercao;
	private boolean disponivel;
    private int retiradas;
    private List<String> observacoes = new ArrayList<>();
	
	public Item() {
		this.dataInsercao = new Date();
		this.disponivel = true;
        this.retiradas = 0;
	}
	
	public void alocaItem() {
        this.disponivel = false;
        this.retiradas++;
    }

    public void devolveItem() {
        this.disponivel = true;
    }

    public void adicionaObservacao(String observacao) {
        this.observacoes.add(observacao);
    }

    public void removeObservacao(String observacao) {
        this.observacoes.remove(observacao);
    }
    
    public void setId(String key) {
    	this.key = key;
    }
    
    public String getId() {
        return key;
    }
    
    public Date getDataInsercao() {
        return dataInsercao;
    }

    public boolean getDisponivel() {
        return disponivel;
    }
    public int getRetiradas(){return retiradas;}

    public List<String> getObservacoes() {
        return observacoes;
    }
    
    public int getNumObservacoes() {
    	return observacoes.size();
    }
}
