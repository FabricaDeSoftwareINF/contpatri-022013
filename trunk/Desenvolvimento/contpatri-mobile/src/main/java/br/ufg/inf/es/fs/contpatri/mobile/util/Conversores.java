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
