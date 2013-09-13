package br.ufg.inf.es.fs.contpatri.mobile.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConexaoInternet {

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
	private ConexaoInternet() {
		
	}
	
}
