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
package br.ufg.inf.es.fs.contpatri.mobile.nucleo;

/**
 * Classe que centraliza informações comuns às classes.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class NucleoApp {

	public static final String TIPO_HASH = "SHA-1";
	public static final String URL_BASE = "http://fs.inf.ufg.br/contpatri/";
	public static final String URL_AUTENTICAR = URL_BASE + "autenticacao";
	public static final String URL_ENVIAR_COLETA = URL_BASE
			+ "inventario/coletas";

	/**
	 * Construtor para não instanciar a classe.
	 */
	private NucleoApp() {

	}

}
