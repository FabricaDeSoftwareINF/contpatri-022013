package br.ufg.inf.es.fs.contpatri.mobile.contripatri;

import java.security.GeneralSecurityException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.controller.FolderManager;
import br.ufg.inf.es.fs.contpatri.mobile.controller.LoginController;
import br.ufg.inf.es.fs.contpatri.mobile.modelo.Usuario;

public final class LoginActivity extends Activity {

	private transient EditText edtLogin;
	private transient EditText edtSenha;
	private transient Usuario usuario;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_login);

		edtLogin = (EditText) findViewById(R.id.login);
		edtSenha = (EditText) findViewById(R.id.senha);

	}
	
	public void onClickLogar(final View view) {
		usuario = new Usuario();
		usuario.setLogin(edtLogin.getText().toString());
		usuario.setSenha(edtSenha.getText().toString());
	}

	private void logar() throws GeneralSecurityException, RemoteException,
			ServiceException {
		
		FolderManager folder = new FolderManager();
		SharedPreferences pref = getApplicationContext().getSharedPreferences(
				"login", MODE_PRIVATE);
		int contador = 0;
		String aux1 = pref.getString("listaLogin", "");
		for (int i = 0; i < aux1.length() - 1; i++) {
			if (aux1.substring(i, i + 1).equals("!;")) {
				contador = contador + 1;

			}
		}

		String[] usuarios = new String[contador];
		aux1 = aux1.substring(0, aux1.length()-2);
		usuarios = aux1.split("!;");
		for (int i = 0; i < usuarios.length; i++) {
			String[] logins = new String[2];
			logins = usuarios[i].split("!:");
			if(logins[0].equals(edtLogin.getText()
				.toString()) && logins[1].equals(edtSenha.getText()
						.toString() )){
				
				
				folder.criarDiretorioParaUsuario(logins[0]);
				
				Intent troca = new
				Intent(LoginActivity.this, TelaSubLocais.class);
						LoginActivity.this.startActivity(troca);
						LoginActivity.this.finish();
						
			}
		}

		LoginController logarController = new LoginController(edtLogin.getText()
				.toString(), edtSenha.getText().toString());
		int resul = logarController.logar();
		if (resul == 0) {
			// abrir janela de erro de login;
		} else if (resul == 1) {

			Editor editor = pref.edit();
			String aux = pref.getString("listaLogin", "");
			editor.putString(
					"listaLogin",
					aux
							+ edtLogin.getText().toString()
							+ "!:"
							+ logarController.hash(edtSenha.getText().toString()
									+ "!;"));
			editor.commit();
			
			folder.criarDiretorioParaUsuario(edtLogin.getText().toString());
			
			Intent troca = new
			Intent(LoginActivity.this, TelaSubLocais.class);
					LoginActivity.this.startActivity(troca);
					LoginActivity.this.finish();
			// mudar activity!
		} else {
			Log.i("que isso jovemm", "como caiu aqui? webservice louco!");
		}

	}

}
