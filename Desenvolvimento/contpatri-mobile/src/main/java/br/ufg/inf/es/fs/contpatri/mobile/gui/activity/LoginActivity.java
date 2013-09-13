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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.gui.dialog.LoginDialog;
import br.ufg.inf.es.fs.contpatri.mobile.util.ConexaoRede;
import br.ufg.inf.es.fs.contpatri.mobile.util.Preferencias;
import br.ufg.inf.es.fs.contpatri.mobile.webservice.Autenticar;

/**
 * Classe que exibe a tela de login e inicia o processo de autenticação do
 * usuário no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class LoginActivity extends Activity {

	private LoginDialog loginDialog;
	private EditText edtLogin;
	private EditText edtSenha;
	private Context contexto;
	private String login;
	private String senha;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_login);
		contexto = this;
		edtLogin = (EditText) findViewById(R.id.edt_login);
		edtSenha = (EditText) findViewById(R.id.edt_senha);
		Preferencias.criarConfiguracoes(this);
		loginDialog = new LoginDialog(this);
	}

	/**
	 * Método que responde ao evento do botão para autenticar as credenciais
	 * digitadas.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void logar(final View view) {

		login = edtLogin.getText().toString();
		senha = edtSenha.getText().toString();

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
					loginDialog
							.mostrar("Não foi possível autenticar devido à limitação na comunicação com o servidor ou ausência da mesma.");
				} else {

					Autenticar autentica = new Autenticar(contexto, login,
							login);
					autentica.execute(new Void[0]);
					Iterator<Entry<Boolean, String>> iterator = autentica
							.getRetorno().entrySet().iterator();

					/*
					 * Verifica se a resposta do WebService for verdadeiro, se
					 * for, as credenciais foram autenticadas e serão
					 * armazenadas no Android, caso contrário exibirá o erro
					 * retornado pelo WebService.
					 */
					if (iterator.next().getKey()) {
						Preferencias.gravarUsuario(login, senha);
						Intent troca = new Intent(LoginActivity.this,
								ListaColetaActivity.class);
						startActivity(troca);
						finish();
					} else {
						loginDialog.mostrar(iterator.next().getValue());
					}
				}

			} else {
				Intent troca = new Intent(LoginActivity.this,
						ListaColetaActivity.class);
				startActivity(troca);
			}

		} else {
			loginDialog.mostrar("Insira as credenciais!");
		}

	}
}
