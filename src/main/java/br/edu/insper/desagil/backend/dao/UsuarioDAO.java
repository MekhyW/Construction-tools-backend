package br.edu.insper.desagil.backend.dao;

import br.edu.insper.desagil.backend.core.Usuario;
import br.pro.hashi.nfp.dao.DAO;

public class UsuarioDAO extends DAO<Usuario> {

	public UsuarioDAO() {
		super("usuarios");
	}

}
