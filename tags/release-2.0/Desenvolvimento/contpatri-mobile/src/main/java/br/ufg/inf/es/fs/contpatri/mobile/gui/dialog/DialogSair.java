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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Classe que exibe uma <code>Dialog</code> caso o usuário solicite a saída do
 * aplicativo.
 * 
 * @author Muryllo Tiraza
 * 
 */
public final class DialogSair {

	private Activity atividade;
	private AlertDialog alertDialog;
	private AlertDialog.Builder dialogoSair;

	/**
	 * Construtor para instanciar o objeto e inicializar as variáveis.
	 * 
	 * @param atividade
	 *            <code>Activity</code> necessária para poder exibir a caixa de
	 *            diálogo
	 */
	public DialogSair(final Activity atividade) {
		this.atividade = atividade;
		this.dialogoSair = new AlertDialog.Builder(atividade);
	}

	/**
	 * Método para exibir a caixa de diálogo.
	 */
	public void confirmaSaida() {

		this.dialogoSair.setTitle("ContPatri");

		this.dialogoSair.setMessage("Deseja sair?");
		this.dialogoSair.setCancelable(false);

		this.dialogoSair.setPositiveButton("Sim",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						atividade.moveTaskToBack(true);
						atividade.finish();
						dialog.cancel();
					}
				});

		this.dialogoSair.setNegativeButton("Não",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		this.alertDialog = this.dialogoSair.create();
		this.alertDialog.show();
	}

}
