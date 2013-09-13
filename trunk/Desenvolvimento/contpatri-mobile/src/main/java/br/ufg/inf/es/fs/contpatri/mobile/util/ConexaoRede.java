package br.ufg.inf.es.fs.contpatri.mobile.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Classe que verifiaca conectividade do aplicativo á rede.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ConexaoRede {

	/**
	 * Método que verifica o status da conexão com a internet.
	 * 
	 * @param contexto
	 *            <code>Context</code> utilizado para iniciar o gerenciador de
	 *            estado de conexão do <b>Android</b>
	 * @return verdadeiro para conexão validada, caso contrário será falsa
	 */
	public static boolean isConnectedInternet(final Context contexto) {
		
		ConnectivityManager conectividade = (ConnectivityManager) contexto
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if (conectividade != null) {
			NetworkInfo[] info = conectividade.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * Construtor para não instanciar a classe.
	 */
	private ConexaoRede() {

	}

}
