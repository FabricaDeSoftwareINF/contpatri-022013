package br.ufg.inf.es.fs.contpatri.mobile.gui.activity;

import org.json.JSONException;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import br.ufg.inf.es.fs.contpatri.mobile.controller.ColetaControler;

public final class ColetaActivity extends Activity {

	private transient EditText tombamento;
	private transient Button bScan;
	private transient Button bConfirma;
	private transient String jsonPath;
	private transient ColetaControler coleta;
	private transient Spinner spinner;
	private transient EditText alteracao;
	private transient EditText sublocal;
	private transient EditText observacao;
	private transient EditText descricao;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);

		jsonPath = getIntent().getExtras().getSerializable("urijson")
				.toString();

		setContentView(R.layout.activity_coleta);

		try {
			coleta = new ColetaControler(jsonPath);
		} catch (JSONException e) {
			Log.e(ColetaActivity.class.getSimpleName(), "", e);
		}
		
		tombamento = (EditText) findViewById(R.id.tombamento);
		alteracao = (EditText) findViewById(R.id.alteracao);
		sublocal = (EditText) findViewById(R.id.sublocal);
		observacao = (EditText) findViewById(R.id.observacao);
		descricao = (EditText) findViewById(R.id.descricao);
		bScan = (Button) findViewById(R.id.bscan);
		bScan.setOnClickListener(bScanListener);
		bConfirma = (Button) findViewById(R.id.bconfirmar);
		bConfirma.setOnClickListener(bConfirmaListener);

	}

	OnClickListener bConfirmaListener = new OnClickListener() {

		public void onClick(View v) {
				coleta.finalizarColeta(spinner.getSelectedItem().toString(),
						observacao.getText().toString());
		}
	};

	OnClickListener bScanListener = new OnClickListener() {
		public void onClick(View v) {

			try {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
				startActivityForResult(intent, 0);
			} catch (final Exception e) {
				Log.e(ColetaActivity.class.getSimpleName(), "", e);
			}

		}
	};

	OnFocusChangeListener nEditorListener = new OnFocusChangeListener() {

		public void onFocusChange(View view, boolean arg1) {
			AsyncTask<Integer, Integer, Integer> coletaTask = new AsyncTask<Integer, Integer, Integer>() {

				@Override
				protected Integer doInBackground(Integer... arg0) {

					try {
						coleta.buscaPatrimonio(tombamento.getText().toString());

						for (int i = 0; i < 3; i++) {
							if (spinner
									.getItemAtPosition(i)
									.toString()
									.equals(coleta.getTombamento()
											.getSituacao())) {
								spinner.setSelection(i);
							}
						}

						alteracao.setText(coleta.getTombamento()
								.getTombamentoUltimaAlteracao());
						sublocal.setText(coleta.getTombamento().getSubLocal());
						observacao.setText(coleta.getTombamento()
								.getObservacao());
						descricao
								.setText(coleta.getTombamento().getDescricao());
					} catch (final JSONException e) {
						Log.e(ColetaActivity.class.getSimpleName(), "", e);
					}

					return 1;
				}

			};
			coletaTask.execute(1);
		}
	};

	@Override
	public void onActivityResult(final int request, final int result,
			final Intent i) {
		if (request == 0) {
			if (result == RESULT_OK) {
				tombamento.setText(i.getStringExtra("SCAN_RESULT"));
			} else if (result == RESULT_CANCELED) {
				tombamento.setText("Scan cancelled.");
			}
		}
	}

}
