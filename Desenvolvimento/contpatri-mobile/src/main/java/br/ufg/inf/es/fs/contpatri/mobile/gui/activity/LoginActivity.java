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
package br.ufg.inf.es.fs.contpatri.mobile.gui.activity;

import java.util.Iterator;
import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.util.ConexaoRede;
import br.ufg.inf.es.fs.contpatri.mobile.util.Preferencias;
import br.ufg.inf.es.fs.contpatri.mobile.webservice.Autenticar;

/**
 * Classe que exibe a tela de login e inicia o processo de autenticação do
 * usuário no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza Santos
 * 
 */
public final class LoginActivity extends Activity {

	private EditText edtLogin;
	private EditText edtSenha;
	private Context contexto;

	/**
	 * Método que responde ao evento do botão para autenticar as credenciais
	 * digitadas.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void logar(final View view) {

		final String login = edtLogin.getText().toString();
		final String senha = edtSenha.getText().toString();

		/*
		 * Verifica se há algo digitado nos campos, se não houver, responderá
		 * com um diálogo dizendo para inserir as credenciais.
		 */
		if (login.length() > 0 && senha.length() > 0) {

			/*
			 * Verifica se o usuário digitado, existe nas configurações do
			 * android, se sim, irá logar na aplicação, senão irá tentar
			 * autenticar com o WebService com as credenciais digitadas.
			 */
			if (!Preferencias.existeUsuario(login, senha)) {

				/*
				 * Verifica o status com a internet, se estiver ok, continua com
				 * o processo de validação dos dados, senão irá mostrar um
				 * diálogo informando o problema.
				 */
				if (!ConexaoRede.isConnectedInternet(this)) {
					mostrarDialogo("Não foi possível autenticar devido à limitação na comunicação com o servidor ou ausência da mesma.");
				} else {

					final Autenticar autentica = new Autenticar(contexto,
							login, senha);
					autentica.execute(new Void[0]);
					final Iterator<Entry<Boolean, String>> iterator = autentica
							.getRetorno().entrySet().iterator();

					/*
					 * Verifica se a resposta do WebService for verdadeiro, se
					 * for, as credenciais foram autenticadas e serão
					 * armazenadas no Android, caso contrário exibirá o erro
					 * retornado pelo WebService.
					 */
					if (iterator.next().getKey()) {
						Preferencias.gravarUsuario(login, senha);
						final Intent troca = new Intent(LoginActivity.this,
								ListaColetaActivity.class);
						startActivity(troca);
						finish();
					} else {
						mostrarDialogo(iterator.next().getValue());
					}
				}

			} else {
				final Intent troca = new Intent(LoginActivity.this,
						ListaColetaActivity.class);
				startActivity(troca);
			}

		} else {
			mostrarDialogo("Insira as credenciais!");
		}

	}

	/**
	 * Método que exibe um <code>Dialog</code> caso haja erro no login do
	 * usuário no aplicativo.
	 * 
	 * @param mensagem
	 *            mensagem de erro que será exibida na <code>Dialog</code> para
	 *            informar o motivo de a aplicação não realizar o login
	 *            corretamente
	 */
	private void mostrarDialogo(final String mensagem) {

		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(this);

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

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_login);
		contexto = this;
		edtLogin = (EditText) findViewById(R.id.edt_login);
		edtSenha = (EditText) findViewById(R.id.edt_senha);
		Preferencias.criarConfiguracoes(this);
	}

	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}
}
