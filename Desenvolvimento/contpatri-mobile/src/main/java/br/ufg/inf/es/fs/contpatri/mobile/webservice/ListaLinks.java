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
package br.ufg.inf.es.fs.contpatri.mobile.webservice;

/**
 * Classe que lista todos os links que o aplicativo utilizará.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ListaLinks {

	/**
	 * Endereço base para todas as requisições do aplicativo.
	 */
	public static final String URL_BASE = "http://fs.inf.ufg.br/contpatri/";

	/**
	 * Endereço para autenticação do aplicativo no WebService.
	 */
	public static final String URL_AUTENTICAR = URL_BASE + "autenticacao";

	/**
	 * Endereço para envio das coletas realizadas ao WebService.
	 */
	public static final String URL_ENVIAR_COLETA = URL_BASE
			+ "inventario/coletas";

	/**
	 * Construtor privado para não instanciar a classe.
	 */
	private ListaLinks() {

	}

}
