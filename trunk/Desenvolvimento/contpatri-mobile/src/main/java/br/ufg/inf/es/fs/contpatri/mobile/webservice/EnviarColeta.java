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

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

/**
 * Classe que cria uma Thread para comunicar com o WebService e enviar todas as
 * coletas que estão pendentes no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class EnviarColeta extends AsyncTask<Void, Integer, Void> {

	private final int timeout = 10000;
	private final ProgressDialog dialog;
	private List<Tombamento> listaTombamento;

	/**
	 * Construtor padrão para instanciar e inicializar o objeto.
	 * 
	 * @param context
	 *            contexto necessário para iniciar o <code>ProgressDialog</code>
	 */
	public EnviarColeta(final Context context) {
		dialog = new ProgressDialog(context);
	}

	@Override
	protected Void doInBackground(final Void... params) {
		/*
		 * final boolean sucesso; final String mensagem;
		 * 
		 * try {
		 * 
		 * 
		 * Ajuste de timeout.
		 */
		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);

		/*
		 * Configurações iniciais para estabelecer uma conexão HTTP.
		 * 
		 * final DefaultHttpClient httpCliente = new DefaultHttpClient(
		 * httpParams); final HttpPost httpPost = new
		 * HttpPost(NucleoApp.URL_ENVIAR_COLETA);
		 * 
		 * 
		 * 
		 * /* Colocando o json do tombamento na requisição.
		 */
		/*
		 * httpPost.setEntity(new ByteArrayEntity(listaTombamento.
		 * .getBytes("UTF8"))); httpPost.setHeader("json", tombamento.toJson());
		 * final ResponseHandler<String> handlerResposta = new
		 * BasicResponseHandler(); final String responseBody =
		 * httpCliente.execute(httpPost, handlerResposta);
		 * 
		 * /* Pega a resposta e transforma para JSON. Depois pega as TAG's para
		 * que depois seja repassada para a tela de login.
		 * 
		 * final JSONObject json = new JSONObject(responseBody); sucesso =
		 * json.getBoolean("sucesso"); mensagem = json.getString("mensagem");
		 * 
		 * publishProgress(1);
		 * 
		 * 
		 * } catch (final UnsupportedEncodingException e) {
		 * Log.e(Autenticar.class.getSimpleName(), "", e); } catch (final
		 * ClientProtocolException e) { Log.e(Autenticar.class.getSimpleName(),
		 * "", e); } catch (final IOException e) {
		 * Log.e(Autenticar.class.getSimpleName(), "", e); } catch (final
		 * JSONException e) { Log.e(Autenticar.class.getSimpleName(), "", e); }
		 */

		return null;
	}

	@Override
	protected void onPostExecute(final Void result) {
		super.onPostExecute(result);
		dialog.dismiss();
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
	protected void onProgressUpdate(final Integer... progresso) {
		dialog.setProgress(progresso[0]);
		dialog.setMessage("Enviando item " + progresso[0]);
	}

}
