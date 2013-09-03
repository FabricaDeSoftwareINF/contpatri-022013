package br.ufg.inf.es.fs.contpatri.mobile.teste;

import android.os.Bundle;
import br.ufg.inf.es.fs.contpatri.mobile.R;

import com.actionbarsherlock.app.SherlockActivity;

public class Teste extends SherlockActivity {

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_main);
		setTheme(com.actionbarsherlock.R.style.Theme_Sherlock_Light_DarkActionBar);
	}

}
