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
	private AlertDialog dialog;

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
		
		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		
	}

}
