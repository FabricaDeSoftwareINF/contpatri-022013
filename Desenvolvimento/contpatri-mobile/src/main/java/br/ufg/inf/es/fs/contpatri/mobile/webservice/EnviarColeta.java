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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
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
	protected Void doInBackground(final Void... params) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(host);

		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

			nameValuePairs.add(new BasicNameValuePair("id", "12345"));
			nameValuePairs.add(new BasicNameValuePair("stringdata",
					"AndDev is Cool!"));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

		} catch (final ClientProtocolException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		} catch (final IOException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
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
