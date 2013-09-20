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
package br.ufg.inf.es.fs.contpatri.mobile.gui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Classe responsável em exibir as informações de erro quando o usuário tentar
 * logar no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class LoginDialog {

	private AlertDialog.Builder builder;

	/**
	 * Construtor padrão para instanciar e inicializar as variáveis.
	 * 
	 * @param contexto
	 *            <code>Context</code> utilizado para inicialiar o
	 *            <code>Dialog</code>
	 */
	public LoginDialog(final Context contexto) {
		builder = new AlertDialog.Builder(contexto);
	}

	/**
	 * Método que constrói e exibe a mensagem de erro.
	 * 
	 * @param mensagem
	 *            mensagem que será exibida pelo <code>Dialog</code>
	 */
	public void mostrar(final String mensagem) {
		
		builder.setTitle("Erro");
		builder.setMessage(mensagem);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		
	}

}
