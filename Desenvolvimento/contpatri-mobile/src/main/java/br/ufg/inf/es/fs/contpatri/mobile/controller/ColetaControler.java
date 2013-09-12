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
package br.ufg.inf.es.fs.contpatri.mobile.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.nucleo.NucleoApp;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

public final class ColetaControler {

	public ColetaControler() {

	}

	public boolean getTombamento() {
		return true;
	}

	public void gerarColeta(final Activity activity, final Tombamento tombamento) {

		File arqTombamento = new File(NucleoApp.LOCAL_COLETAS
				+ tombamento.getCodigo() + ".json");

		if (arqTombamento.exists()) {
			arqTombamento.delete();
		}

		try {

			arqTombamento.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(arqTombamento);
			fos.write(tombamento.toJson().getBytes());
			fos.close();

			AlertDialog.Builder builder;
			builder = new AlertDialog.Builder(activity);
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.setTitle("Sucesso");
			builder.setMessage("Tombamento " + tombamento.getCodigo()
					+ " gerado com sucesso!\nArquivo gerado "
					+ arqTombamento.getName());
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(final DialogInterface dialog,
								int which) {
							activity.finish();
						}
					});
			final AlertDialog dialog = builder.create();
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();

		} catch (final IOException e) {
			Log.e(ColetaControler.class.getSimpleName(), "", e);
		}

	}

}
