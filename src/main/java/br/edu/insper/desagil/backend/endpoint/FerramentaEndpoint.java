package br.edu.insper.desagil.backend.endpoint;

import java.util.List;

import br.edu.insper.desagil.backend.core.Ferramenta;
import br.edu.insper.desagil.backend.core.Item;
import br.edu.insper.desagil.backend.dao.FerramentaDAO;
import br.edu.insper.desagil.backend.dao.ItemDAO;
import br.pro.hashi.nfp.dao.Selection;
import br.pro.hashi.nfp.rest.server.Args;
import br.pro.hashi.nfp.rest.server.Endpoint;

public class FerramentaEndpoint extends Endpoint<Ferramenta> {
	private FerramentaDAO ferramentaDAO = new FerramentaDAO();
	private ItemDAO itemDAO = new ItemDAO();

	public FerramentaEndpoint() {
		super("/ferramenta");
	}
	
	@Override
	public Ferramenta get(Args args) {
		String nome = args.get("nome");
		Selection selection = ferramentaDAO.selectWhereEqualTo("nome", nome);
		List<Ferramenta> ferramentas = ferramentaDAO.retrieve(selection);
		Ferramenta ferramenta = ferramentas.get(0);
	    return ferramenta;
	}

	@Override
	public Object post(Args args, Ferramenta ferramenta) {
		String nome = args.get("nome");
		Ferramenta novaFerramenta = new Ferramenta();
		ferramentaDAO.create(novaFerramenta);
		novaFerramenta.setNome(nome);
		ferramentaDAO.update(novaFerramenta);
		return novaFerramenta;
	}

	@Override
	public Object put(Args args, Ferramenta ferramenta) {
		String comando = args.get("comando");
		String itemId;
		Item item;
		switch(comando) {
		case "setNome":
			String nome = args.get("nome");
			ferramenta.setNome(nome);
			break;
		case "adicionaItem":
			itemId = args.get("itemId");
			item = itemDAO.retrieve(itemId);
			ferramenta.adicionaItem(item);
			break;
		case "removeItem":
			itemId = args.get("itemId");
			item = itemDAO.retrieve(itemId);
			ferramenta.adicionaItem(item);
			break;
		}
		ferramentaDAO.update(ferramenta);
		return ferramenta;
	}

	@Override
	public Object delete(Args args) {
		String id = args.get("id");
		ferramentaDAO.delete(id);
	    return null;
	}

}
