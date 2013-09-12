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

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza
 * @author Fellipe Cesar
 * @author Thiago Fernandes
 * 
 */
public final class EnviarColeta extends AsyncTask<Void, Integer, Void> {

	private transient final ProgressDialog dialog;
	private transient final String host;
	private transient final List<Tombamento> listaTombamento;

	/**
	 * Construtor padrão para instanciar e inicializar o objeto.
	 * 
	 * @param context
	 *            contexto necessário para iniciar o <code>ProgressDialog</code>
	 * @param url
	 *            url para conexão com o WebService
	 */
	public EnviarColeta(final Context context, final String url,
			final List<Tombamento> lista) {
		dialog = new ProgressDialog(context);
		host = url;
		listaTombamento = lista;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog.setTitle("Sincronizando...");
		dialog.setMessage("Aguarde a sincronização");
		dialog.setIndeterminate(false);
		dialog.setMax(listaTombamento.size());
		dialog.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(final Integer... progresso) {
		dialog.setProgress(progresso[0]);
		dialog.setMessage("Enviando item " + progresso[0]);
	}

	@Override
	protected void onPostExecute(final Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

}
