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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.TombamentoDAO;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class EnviarColeta extends AsyncTask<Void, Integer, Void> {

	private final int timeout = 10000;
	private final Activity activity;
	private final ProgressDialog dialog;
	private String mensagem;
	private boolean sucesso;
	private TombamentoDAO tmbDAO;

	public EnviarColeta(final Activity actv) {
		dialog = new ProgressDialog(actv);
		tmbDAO = new TombamentoDAO(actv);
		activity = actv;
	}

	@Override
	protected Void doInBackground(final Void... params) {

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
		final HttpPost httpPost = new HttpPost(ListaLinks.URL_ENVIAR_COLETA);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		HttpResponse httpResponse;

		try {

			httpPost.setEntity(new StringEntity(tmbDAO.getTodosJson()));
			httpResponse = httpCliente.execute(httpPost);

			/*
			 * Se a resposta ao servidor for uma de código maior ou igual a 400,
			 * significa que houve erro que não houve comunicação com o
			 * WebService. Caso contrário a comunicação foi bem sucedida.
			 */
			if (httpResponse.getStatusLine().getStatusCode() >= 400) {
				sucesso = false;
				mensagem = httpResponse.getStatusLine().getReasonPhrase();
			} else {

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(httpResponse.getEntity()
								.getContent(), "UTF-8"));
				StringBuilder builder = new StringBuilder();

				for (String line = null; (line = reader.readLine()) != null;) {
					builder.append(line).append("\n");
				}

				final JSONObject json = new JSONObject(builder.toString());

				sucesso = json.getBoolean("sucesso");
				mensagem = json.getString("mensagem");

			}

		} catch (final UnsupportedEncodingException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		} catch (final ClientProtocolException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		} catch (final IOException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		} catch (final JSONException e) {
			Log.e(EnviarColeta.class.getSimpleName(), "", e);
		}

		return null;
	}

	@Override
	protected void onPostExecute(final Void result) {
		super.onPostExecute(result);
		tmbDAO.fecharConexao();

		/*
		 * Verifica se teve sucesso no envio, se teve, apenas fechará a caixa de
		 * diálogo, caso contrário irá mostrar uma outra caixa de diálogo que
		 * informará o erro.
		 */
		if (sucesso) {
			Log.v(EnviarColeta.class.getSimpleName(),
					"Envio de realizado com sucesso");
		} else {
			mostrarDialogo();
		}

		dialog.dismiss();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		tmbDAO.abrirConexao();
		dialog.setTitle("Sincronizando...");
		dialog.setMessage("Aguarde a sincronização");
		dialog.setIndeterminate(true);
		dialog.show();
	}

	/**
	 * Método que exibe um <code>Dialog</code> caso haja erro no login do
	 * usuário no aplicativo.
	 */
	private void mostrarDialogo() {

		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(activity);

		builder.setTitle("Erro");
		builder.setMessage(mensagem);
		builder.setIcon(android.R.drawable.ic_dialog_alert);

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}
		});

		final AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

}
