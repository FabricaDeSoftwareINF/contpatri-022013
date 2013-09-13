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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.ufg.inf.es.fs.contpatri.mobile.nucleo.NucleoApp;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class Autenticar extends AsyncTask<Void, Integer, Void> {

	private HttpResponse response;
	private final ProgressDialog dialog;
	private final String usuario;
	private final String senha;
	private Map<Boolean, String> retorno;

	/**
	 * Construtor padrão para instanciar e inicializar o objeto.
	 * 
	 * @param context
	 *            contexto necessário para iniciar o <code>ProgressDialog</code>
	 * @param url
	 *            url para conexão com o WebService
	 */
	public Autenticar(final Context context, final String user,
			final String pass) {
		dialog = new ProgressDialog(context);
		retorno = new HashMap<Boolean, String>();
		usuario = user;
		senha = pass;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog.setTitle("Autenticando...");
		dialog.setMessage("Realizando login com " + usuario);
		dialog.setIndeterminate(true);
		dialog.show();
	}

	@Override
	protected Void doInBackground(final Void... params) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(NucleoApp.URL_AUTENTICAR);

		try {

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

			nameValuePairs.add(new BasicNameValuePair("login", usuario));
			nameValuePairs.add(new BasicNameValuePair("senha", senha));

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			response = httpclient.execute(httppost);

		} catch (final ClientProtocolException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		} catch (final IOException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		}
		return null;
	}

	@Override
	protected void onPostExecute(final Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
	}

	/**
	 * Método que retorna o resultado da requisição de autenticação.
	 * 
	 * @return retorna um <code>Map> do tipo <code>Boolean</code> e
	 *         <code>String</code> onde, o primeiro se for verdadeiro, a
	 *         autenticação foi bem sucedida, caso contrário será falso e, no
	 *         segundo elemento, será a mensagem de retorno de erro
	 */
	public Map<Boolean, String> getRetorno() {
		retorno.put(true, "OK");
		return retorno;
	}

}
