package br.ufg.inf.es.fs.contpatri.mobile.usuario;

import com.google.gson.GsonBuilder;

/**
 * Classe modelo do usuário que requisitará a autenticação no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class Usuario {

	private String login;
	private String senha;

	/**
	 * Construtor padrão.
	 */
	public Usuario() {

	}

	/**
	 * Construtor que inicia o objeto já com os atributos inicializados.
	 * 
	 * @param user usuário que requisitará autenticação
	 * @param pass senha do usuário da requisição
	 */
	public Usuario(final String user, final String pass) {
		login = user;
		senha = pass;
	}

	/**
	 * @return retorna a senha do usuário
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * 
	 * @param senha atribui uma nova senha para o usuário
	 */
	public void setSenha(final String senha) {
		this.senha = senha;
	}

	/**
	 * 
	 * @return retorna o login do usuário
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * 
	 * @param login atribui um novo login ao usuário
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * 
	 * @return retorna o json do objeto usuário
	 */
	public String toJson() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}

}