package br.ufg.inf.es.fs.contpatri.mobile.util;

import android.os.Environment;

public class Armazenamento {

	public static class Externo {
		
		public static boolean getEstadoValido() {
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				return true;
			} else {
				return false;
			} 
		}
		
	}
		
}
