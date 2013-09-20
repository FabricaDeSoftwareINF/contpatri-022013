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
