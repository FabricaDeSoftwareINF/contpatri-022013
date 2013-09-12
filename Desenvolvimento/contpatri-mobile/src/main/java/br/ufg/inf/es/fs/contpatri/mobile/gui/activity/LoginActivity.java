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

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.ufg.inf.es.fs.contpatri.mobile.controller.LoginController;
import br.ufg.inf.es.fs.contpatri.mobile.util.FolderManager;
import br.ufg.inf.es.fs.contpatri.mobile.util.Preferencias;

import br.ufg.inf.es.fs.contpatri.mobile.R;

/**
 * Classe que exibe a tela de login e inicia o processo de autenticação do
 * usuário no aplicativo.
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza
 * @author Fellipe Cesar
 * @author Thiago Fernandes
 * 
 */
public final class LoginActivity extends Activity {

	private transient LoginController logarController;

	private transient EditText edtLogin;
	private transient EditText edtSenha;
	private transient Context contexto;
	private transient String login;
	private transient String senha;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_login);
		contexto = this;
		edtLogin = (EditText) findViewById(R.id.edt_login);
		edtSenha = (EditText) findViewById(R.id.edt_senha);
		Preferencias.criarConfiguracoes(this);
	}

	public void logar(final View view) {

		login = edtLogin.getText().toString();
		senha = edtSenha.getText().toString();

		try {

			if (!Preferencias.existeUsuario(login, senha)) {

				logarController = new LoginController(login, senha);

				if (logarController.logar()) {

					Preferencias.gravarUsuario(login, senha);

				} else {
					Toast.makeText(contexto,
							"Erro ao logar, tente novamente mais tarde",
							Toast.LENGTH_LONG).show();
				}

			}

			if (FolderManager.criaDiretorio(this)) {
				Intent troca = new Intent(LoginActivity.this,
						ListaColetaActivity.class);
				startActivity(troca);
				finish();
			} else {
				Toast.makeText(contexto, "Falha no login, tente novamente",
						Toast.LENGTH_LONG).show();
			}

		} catch (final NoSuchAlgorithmException e) {
			Log.e(LoginActivity.class.getSimpleName(),
					"Erro ao logar, tente novamente mais tarde", e);
		}

	}

}
