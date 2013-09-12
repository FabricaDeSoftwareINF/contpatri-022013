package br.ufg.inf.es.fs.contpatri.mobile.nucleo;

import java.io.File;

import android.os.Environment;

/**
 * Classe que centraliza informações comuns às classes.
 * 
 * @author Rogério Tristão Junior
 *
 */
public final class NucleoApp {

	public static final transient String TIPO_HASH = "SHA-1";
	public static final transient String LOCAL_ARMAZENAMENTO = Environment.getExternalStorageDirectory().toString() + "/ContPatri/";
	public static final transient String LOCAL_COLETAS = LOCAL_ARMAZENAMENTO + "Coletas/";
	public static final transient String LOCAL_RELATORIOS = LOCAL_ARMAZENAMENTO + "Relatorios/";
	
	public static void criaPastas() {
		new File(LOCAL_RELATORIOS).mkdirs();
		new File(LOCAL_COLETAS).mkdirs();
	}
	
}
