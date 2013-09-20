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

import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.gui.activity.ColetaActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TableRow;

/**
 * Classe que exibe o menu na tela de coleta.
 * 
 * @author Muryllo Tiraza Santos
 * @author Rogério Tristão Junior
 * 
 */
public class DialogMenuColeta {

	private Dialog dialog;
	private Activity activity;

	private TableRow coletar;
	private TableRow relatorio;
	private TableRow exibir;

	private Intent intent;

	/**
	 * Construtor da classe. <br>Recebe uma activity para montar o dialog.
	 * 
	 * @param activity
	 */
	public DialogMenuColeta(Activity activity) {
		this.activity = activity;
		this.dialog = new Dialog(activity);
	}

	/**
	 * Método que monta, defini propiedades e exibe o dialog de menu na tela.
	 */
	public void exibeDialog() {
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_menu_coleta);
		dialog.setCanceledOnTouchOutside(true);

		coletar = (TableRow) dialog.findViewById(R.id.tbrColeta);
		relatorio = (TableRow) dialog.findViewById(R.id.tbrRelatorio);
		exibir = (TableRow) dialog.findViewById(R.id.tbrExibir);

		coletar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickColetar();
			}
		});

		relatorio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickRelatorio();
			}
		});

		exibir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickExibir();
			}
		});

		dialog.show();
	}

	/**
	 * Click na TableRow COLETAR.
	 */
	private void onClickColetar() {
		intent = new Intent(activity, ColetaActivity.class);
		activity.startActivity(intent);
		dialog.dismiss();
	}

	/**
	 * Click na TableRow REALTORIO.
	 */
	private void onClickRelatorio() {
		dialog.dismiss();
	}

	/**
	 * Click na TableRow EXIBIR.
	 */
	private void onClickExibir() {
		dialog.dismiss();
	}

}
