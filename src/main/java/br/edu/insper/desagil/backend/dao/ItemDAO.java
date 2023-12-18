package br.edu.insper.desagil.backend.dao;

import br.edu.insper.desagil.backend.core.Item;
import br.pro.hashi.nfp.dao.DAO;

public class ItemDAO extends DAO<Item> {

	public ItemDAO() {
		super("itens");
	}

}
