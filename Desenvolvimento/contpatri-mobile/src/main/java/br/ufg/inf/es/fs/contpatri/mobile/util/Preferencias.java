package br.ufg.inf.es.fs.contpatri.mobile.util;

import java.security.NoSuchAlgorithmException;

import br.ufg.inf.es.fs.contpatri.mobile.controller.LoginController;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public final class Preferencias {

	private transient final SharedPreferences conf;
	
	public Preferencias(final Context contexto) {
		conf = contexto.getSharedPreferences("login", Context.MODE_PRIVATE);
	}

	public boolean existeUsuario(final String login, final String senha) {
		if (conf.getString(login, "") == "") {
			return false;
		} else {
			return true;
		}
	}

	public void gravarUsuario(final LoginController logarController,
			final String login, final String senha) {
		Editor editor = conf.edit();
		if (conf.getString(login, "") == "") {
			try {
				editor.putString(login, logarController.hash(senha));
				editor.commit();
			} catch (final NoSuchAlgorithmException e) {
				Log.e(Preferencias.class.getSimpleName(), "", e);
			}
		}
	}
}
