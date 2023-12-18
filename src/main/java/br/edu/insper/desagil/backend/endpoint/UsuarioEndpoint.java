package br.edu.insper.desagil.backend.endpoint;

import br.edu.insper.desagil.backend.core.Usuario;
import br.edu.insper.desagil.backend.dao.UsuarioDAO;
import br.pro.hashi.nfp.dao.Selection;
import br.pro.hashi.nfp.rest.server.Args;
import br.pro.hashi.nfp.rest.server.Endpoint;

import java.util.List;

public class UsuarioEndpoint extends Endpoint<Usuario>{
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public UsuarioEndpoint() {
		super("/usuario");
	}
	
	@Override
	public Usuario get(Args args) {
		String login = args.get("login");
		String senha = args.get("senha");
		Selection selection = usuarioDAO.selectWhereEqualTo("login", login);
		List<Usuario> usuarios = usuarioDAO.retrieve(selection);
		if (usuarios.size() > 0) {
			Usuario usuario = usuarios.get(0);
			if (usuario != null) {
				if (usuario.getSenha().equals(senha)) {
					return usuario;
				}
			}
		}
	    return null;
	}
	
	@Override
	public Object post(Args args, Usuario usuario) {
		String login = args.get("login");
		String senha = args.get("senha");
		boolean admin = args.get("admin").equals("true");
		Usuario novoUsuario = new Usuario();
		usuarioDAO.create(novoUsuario);
		novoUsuario.setLogin(login);
		novoUsuario.setSenha(senha);
		novoUsuario.setAdmin(admin);
		usuarioDAO.update(novoUsuario);
		return novoUsuario;
	}
	
	@Override
	public Object put(Args args, Usuario usuario) {
		String comando = args.get("comando");
		String login;
		String senha;
		boolean admin;
		switch (comando) {
			case "setLogin":
				login = args.get("login");
				usuario.setLogin(login);
				break;
			case "setSenha":
				senha = args.get("senha");
				usuario.setSenha(senha);
				break;
			case "setAdmin":
				String adminString = args.get("admin");
				admin = Boolean.parseBoolean(adminString);
				usuario.setAdmin(admin);
				break;
		}
		usuarioDAO.update(usuario);
		return usuario;
	}

	@Override
	public Object delete(Args args) {
		String login = args.get("login");
		Selection selection = usuarioDAO.selectWhereEqualTo("login", login);
		List<Usuario> usuarios = usuarioDAO.retrieve(selection);
		Usuario usuario = usuarios.get(0);
		usuarioDAO.delete(usuario.getId());
	    return null;
	}

}
