package br.edu.insper.desagil.backend.endpoint;

import br.edu.insper.desagil.backend.core.Item;
import br.edu.insper.desagil.backend.dao.ItemDAO;
import br.pro.hashi.nfp.rest.server.Args;
import br.pro.hashi.nfp.rest.server.Endpoint;

public class ItemEndpoint extends Endpoint<Item> {
	ItemDAO itemDAO = new ItemDAO();

	public ItemEndpoint() {
		super("/item");
	}
	
	@Override
	public Item get(Args args) {
		String key = args.get("key");
		Item item = itemDAO.retrieve(key);
	    return item;
	}

	@Override
	public Object post(Args args, Item item) {
		Item novoItem = new Item();
		itemDAO.create(novoItem);
		return novoItem;
	}

	@Override
	public Object put(Args args, Item item) {
		String comando = args.get("comando");
		String observacao;
		switch (comando) {
		case "alocaItem":
			item.alocaItem();
			break;
		case "devolveItem":
			item.devolveItem();
			break;
		case "adicionaObservacao":
			observacao = args.get("observacao");
			item.adicionaObservacao(observacao);
			break;
		case "removeObservacao":
			observacao = args.get("observacao");
			item.removeObservacao(observacao);
			break;
		}
		itemDAO.update(item);
		return item;
	}

	@Override
	public Object delete(Args args) {
		String key = args.get("key");
		itemDAO.delete(key);
	    return null;
	}

}
