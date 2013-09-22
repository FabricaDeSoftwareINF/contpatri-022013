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

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.gui.dialog.DialogSair;
import br.ufg.inf.es.fs.contpatri.mobile.usuario.Usuario;
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
		final Usuario usuario = new Usuario(login, senha);
			
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
					Autenticar.mostrarDialogo(this, "Não foi possível autenticar devido à limitação na comunicação com o servidor ou ausência da mesma.");
				} else {
					new Autenticar(this, usuario).execute(new Void[0]);
				}

			} else {
				final Intent troca = new Intent(LoginActivity.this,
						ListaColetaActivity.class);
				startActivity(troca);
			}

		} else {
			Autenticar.mostrarDialogo(this, "Insira as credenciais!");
		}

	}

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_login);
		edtLogin = (EditText) findViewById(R.id.edt_login);
		edtSenha = (EditText) findViewById(R.id.edt_senha);
		Preferencias.criarConfiguracoes(this);
	}

	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		
		/*
		 * Útil para uma melhor visualização do background em alguns
		 * dispositivos.
		 */
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	
    @Override
    public void onBackPressed() {
    	new DialogSair(this).confirmaSaida();
    }

}
