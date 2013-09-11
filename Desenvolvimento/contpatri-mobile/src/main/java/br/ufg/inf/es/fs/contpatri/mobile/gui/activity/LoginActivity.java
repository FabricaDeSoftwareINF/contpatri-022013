package br.ufg.inf.es.fs.contpatri.mobile.gui.activity;

import java.security.NoSuchAlgorithmException;

import android.R;
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

public final class LoginActivity extends Activity {

	private transient Preferencias prefs;
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
		prefs = new Preferencias(contexto);
	}

	public void logar(final View view) {

		login = edtLogin.getText().toString();
		senha = edtSenha.getText().toString();

		try {

			if (!prefs.existeUsuario(login, senha)) {
				
				logarController = new LoginController(login, senha);
				
				if (logarController.logar()) {
					
					prefs.gravarUsuario(logarController, login, senha);
					
				} else {
					Toast.makeText(contexto, "Erro ao logar, tente novamente mais tarde", Toast.LENGTH_LONG).show();
				}
				
			}
			
			if (FolderManager.criaDiretorio(this)) {
				Intent troca = new Intent(LoginActivity.this, SubLocaisActivity.class);
				startActivity(troca);
				finish();
			} else {
				Toast.makeText(contexto, "Falha no login, tente novamente", Toast.LENGTH_LONG).show();
			}
			
		} catch (final NoSuchAlgorithmException e) {
			Log.e(LoginActivity.class.getSimpleName(), "Erro ao logar, tente novamente mais tarde", e);
		}

	}

}
