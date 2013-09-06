package br.ufg.inf.es.fs.contpatri.mobile.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufg.inf.es.fs.contpatri.mobile.modelo.Usuario;

public final class LoginController {

	private transient final Usuario usuario;
	private transient final String host;
	
	public LoginController(final Usuario user, final String url) {
		usuario = user;
		host = url;
	}

	public int logar() {
		
		Call call = (Call) new Service().createCall();
		call.setTargetEndpointAddress(host);
		call.setOperationName("logar");

		Object[] param = new Object[] { usuario.getLogin(), hash(usuario.getSenha()) };
		Integer ret = (Integer) call.invoke(param);

		return ret;
	}

	private String hash(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
		String crypto = hash.toString(16);
		return crypto;
	}

}
