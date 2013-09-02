package br.ufg.inf.fs.contripatri;

import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.xml.rpc.ServiceException;

import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.fs.controller.FolderManager;
import br.ufg.inf.fs.controller.LoginController;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginActivity extends Activity {

	public Button bLogin;
	public EditText login;
	public EditText senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		bLogin = (Button) findViewById(R.id.blogin);
		login = (EditText) findViewById(R.id.login);
		senha = (EditText) findViewById(R.id.senha);

		bLogin.setOnClickListener(bLoginListener);
	}

	public OnClickListener bLoginListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			try {
				logar();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

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
			if(logins[0].equals(login.getText()
				.toString()) && logins[1].equals(senha.getText()
						.toString() )){
				
				
				folder.criarDiretorioParaUsuario(logins[0]);
				
				Intent troca = new
				Intent(LoginActivity.this, TelaSubLocais.class);
						LoginActivity.this.startActivity(troca);
						LoginActivity.this.finish();
						 
				
			}
		}

		LoginController logarController = new LoginController(login.getText()
				.toString(), senha.getText().toString());
		int resul = logarController.logar();
		if (resul == 0) {
			// abrir janela de erro de login;
		} else if (resul == 1) {

			Editor editor = pref.edit();
			String aux = pref.getString("listaLogin", "");
			editor.putString(
					"listaLogin",
					aux
							+ login.getText().toString()
							+ "!:"
							+ logarController.hash(senha.getText().toString()
									+ "!;"));
			editor.commit();
			
			folder.criarDiretorioParaUsuario(login.getText().toString());
			
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
