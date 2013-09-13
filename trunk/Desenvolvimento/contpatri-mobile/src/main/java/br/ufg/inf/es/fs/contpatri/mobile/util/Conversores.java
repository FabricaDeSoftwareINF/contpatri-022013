package br.ufg.inf.es.fs.contpatri.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Classe que unifica a conversão de informações no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class Conversores {

	/**
	 * Converte um <code>Long</code> no formato de data e hora brasileiro
	 * (DD/MM/YYY - HH:MM:SS).
	 * 
	 * @param numero
	 *            número que será convertido para data
	 * @return retorna uma <code>String</code> que representa a data do
	 *         parâmetro de entrada
	 */
	public static String longToDate(final long numero) {
		return new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss",
				Locale.getDefault()).format(numero);
	}

	/**
	 * Construtor para não instanciar a classe.
	 */
	private Conversores() {

	}

}
