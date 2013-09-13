/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ 
 * Instituto de Informática UFG: http://www.inf.ufg.br 
 * Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013 
 *
 * Copyleft © UFG.
 *
 * Licenciado sobre a licença GNU-GPL v3
 *
 * Você pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html
 *
 * A menos que especificado ou exigido por legislação local, o software é
 * fornecido "da maneira que está", sem garantias ou condições de qualquer tipo,
 * nem expressas nem implícitas. Em caso de dúvidas referir a licença GNU-GPL.
 */
package br.ufg.inf.es.fs.contpatri.mobile.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufg.inf.es.fs.contpatri.mobile.nucleo.NucleoApp;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Classe que gerencia as configurações da aplicação
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza
 * @author Fellipe Cesar
 * @author Thiago Fernandes
 * 
 */
public final class Preferencias {

	/**
	 * Construtor para não instanciar a classe.
	 */
	private Preferencias() {

	}

	private transient static SharedPreferences configuracoes;

	/**
	 * Método que cria as configurações do aplicativo e instancia a classe
	 * <code>SharedPreferences</code>.
	 * 
	 * @param contexto
	 *            <code>Context</code> utilizado para instanciar a classe
	 *            <code>SharedPreferences</code>
	 */
	public static void criarConfiguracoes(final Context contexto) {
		configuracoes = contexto.getSharedPreferences("login",
				Context.MODE_PRIVATE);
	}

	/**
	 * Método que verifica se existe o usuário nas configurações do Android.
	 * 
	 * @param login
	 *            login utilizado pelo usuário
	 * @param senha
	 *            senha utilizado pelo usuário
	 * @return verdadeiro para usuário existente. Caso contrário, será falso.
	 */
	public static boolean existeUsuario(final String login, final String senha) {
		return configuracoes.getString(login, "").equals("");
	}

	/**
	 * Grava o login e a senha do usuário que fora validado pelo WebService nas
	 * configurações do Android.
	 * 
	 * @param login
	 *            login utilizado pelo usuário
	 * @param senha
	 *            senha utilizado pelo usuário
	 */
	public static void gravarUsuario(final String login, final String senha) {
		Editor editor = configuracoes.edit();
		if (configuracoes.getString(login, "").equals("")) {
			editor.putString(login, mascararSenha(senha));
			editor.commit();
		}
	}

	/**
	 * Método que mascara a senha do usuário em uma <code>String</code>.
	 * 
	 * @param senha
	 *            senha do usuário
	 * @return retorna a <code>String</code> codificada do usuário
	 */
	public static String mascararSenha(final String senha) {

		String pass = "";
		MessageDigest md;

		try {
			md = MessageDigest.getInstance(NucleoApp.TIPO_HASH);
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			pass = hash.toString(16);
		} catch (final NoSuchAlgorithmException e) {
			Log.e(Preferencias.class.getSimpleName(), "", e);
		}

		return pass;
	}
}
