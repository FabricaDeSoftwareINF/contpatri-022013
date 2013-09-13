package br.ufg.inf.es.fs.contpatri.mobile.gui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class LoginDialog {

	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	
	public LoginDialog(final Context contexto) {
		builder = new AlertDialog.Builder(contexto);
	}
	
	public void mostrar(final String mensagem) {
		builder.setTitle("Erro");
		builder.setMessage(mensagem);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setPositiveButton("Ok",
				new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							int which) {
						dialog.dismiss();
					}
				});
		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}
	
}
