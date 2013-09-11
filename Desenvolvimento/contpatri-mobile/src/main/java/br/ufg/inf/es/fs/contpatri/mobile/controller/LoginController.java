package br.ufg.inf.es.fs.contpatri.mobile.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class LoginController {

	private String login;
	private String senha;

	public LoginController(final String user, final String pass)
			throws NoSuchAlgorithmException {
		login = user;
		senha = pass;
	}

	public boolean logar() {
		/*
		 * String local = "http://localhost:8080/axis/Servico.jws";
		 * 
		 * // Criando e configurando o serviço Call call = (Call) new
		 * Service().createCall(); // Configurando o endereço.
		 * call.setTargetEndpointAddress(local); // Marcando o método a ser
		 * chamado. call.setOperationName("logar");
		 * 
		 * // Parâmetros da função soma. Object[] param = new Object[] { login,
		 * hash(senha) }; // Retorno da Função // Integer ret = (Integer)
		 * call.invoke(param);
		 */

		return true;
	}

	public String hash(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		String crypto = hash.toString(16);
		return crypto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
