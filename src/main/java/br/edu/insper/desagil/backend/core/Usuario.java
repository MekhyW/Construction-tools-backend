package br.edu.insper.desagil.backend.core;

import br.pro.hashi.nfp.dao.Autokey;

public class Usuario {
	@Autokey
	private String id;
	private String login;
	private String senha;
	private boolean admin;
	
	public Usuario() {
		this.login = "default";
		this.senha = new String();
		this.admin = false;
	}

	public String getId() {
		return  id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
