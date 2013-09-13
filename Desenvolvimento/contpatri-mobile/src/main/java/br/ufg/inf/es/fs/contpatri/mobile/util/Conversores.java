package br.ufg.inf.es.fs.contpatri.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public final class Conversores {

	public static String longToDate(final long numero) {
		return new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.getDefault()).format(numero);
	}
	
}
