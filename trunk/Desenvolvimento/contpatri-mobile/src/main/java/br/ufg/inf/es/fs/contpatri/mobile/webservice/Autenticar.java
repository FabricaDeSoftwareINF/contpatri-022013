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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class Autenticar extends AsyncTask<Void, Integer, Void> {

	private final ProgressDialog dialog;
	private final String usuario;
	private final String senha;
	private final Map<Boolean, String> retorno;
	private boolean sucesso;
	private String mensagem;
	private final int timeout = 10000;

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
	protected Void doInBackground(final Void... params) {

		/*
		 * Parâmetros a serem utilizados.
		 */
		final ArrayList<NameValuePair> listaParametros = new ArrayList<NameValuePair>();
		listaParametros.add(new BasicNameValuePair("login", usuario));
		listaParametros.add(new BasicNameValuePair("senha", senha));

		/*
		 * Ajuste de timeout.
		 */
		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);

		/*
		 * Configurações iniciais para estabelecer uma conexão HTTP.
		 */
		final DefaultHttpClient httpCliente = new DefaultHttpClient(httpParams);
		final HttpPost httpPost = new HttpPost(ListaLinks.URL_AUTENTICAR);
		HttpResponse httpResponse;

		try {

			httpPost.setEntity(new UrlEncodedFormEntity(listaParametros));
			httpResponse = httpCliente.execute(httpPost);

			if (httpResponse.getStatusLine().getStatusCode() >= 400) {
				sucesso = false;
				mensagem = httpResponse.getStatusLine().getReasonPhrase();
			} else {

				final ResponseHandler<String> handlerResposta = new BasicResponseHandler();
				final String resposta = handlerResposta
						.handleResponse(httpResponse);

				/*
				 * Pega a resposta e transforma para JSON. Depois pega as TAG's
				 * para que depois seja repassada para a tela de login.
				 */
				final JSONObject json = new JSONObject(resposta);
				sucesso = json.getBoolean("sucesso");
				mensagem = json.getString("mensagem");

			}

		} catch (final UnsupportedEncodingException e) {
			Log.e(Autenticar.class.getSimpleName(), "", e);
		} catch (final ClientProtocolException e) {
			Log.e(Autenticar.class.getSimpleName(), "", e);
		} catch (final IOException e) {
			Log.e(Autenticar.class.getSimpleName(), "", e);
		} catch (final JSONException e) {
			Log.e(Autenticar.class.getSimpleName(), "", e);
		}

		return null;
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
		// retorno.put(sucesso, mensagem);
		retorno.put(true, mensagem);
		return retorno;
	}

	@Override
	protected void onPostExecute(final Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
		Log.e("", String.valueOf(sucesso));
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog.setTitle("Autenticando...");
		dialog.setMessage("Realizando login com " + usuario);
		dialog.setIndeterminate(true);
		dialog.show();
	}

}
