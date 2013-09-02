package br.ufg.inf.fs.contripatri;

import org.json.JSONException;

import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.fs.controller.ColetaControler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ColetaActivity extends Activity {
	EditText tombamento;
	Button bScan;
	Button bConfirma;
	String jsonPath;
	ColetaControler coleta;
	Spinner spinner;
	EditText alteracao;
	EditText sublocal;
	EditText observacao;
	EditText descricao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		jsonPath =  getIntent().getExtras()
			.getSerializable("urijson").toString();
		
		setContentView(R.layout.activity_coleta);
		
		try {
			coleta = new ColetaControler(jsonPath);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		@Override
		public void onClick(View v) {
			try {
				coleta.finalizarColeta(spinner.getSelectedItem().toString(), observacao.getText().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	};
	
	OnClickListener bScanListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			try {
				
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
				startActivityForResult(intent, 0);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "ERROR:" + e, 1).show();

			}

		}
	};

	
	OnFocusChangeListener nEditorListener = new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View arg0, boolean arg1) {
			// TODO Auto-generated method stub
			AsyncTask<Integer, Integer, Integer> coletaTask = new AsyncTask<Integer, Integer, Integer>(){

				@Override
				protected Integer doInBackground(Integer... arg0) {
					// TODO Auto-generated method stub
					
				try {
					coleta.buscaPatrimonio(tombamento.getText().toString());
			
				for(int i = 0; i < 3; i++ ){
					
						if(spinner.getItemAtPosition(i).toString().equals(coleta.getTombamento().getSituacao())){
							spinner.setSelection(i);
						}
					
						// TODO Auto-generated catch block
						
					
				}
			
					alteracao.setText(coleta.getTombamento().getTombamentoUltimaAlteracao());
					sublocal.setText(coleta.getTombamento().getSubLocal());
					observacao.setText(coleta.getTombamento().getObservacao());
					descricao.setText(coleta.getTombamento().getDescricao());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
				
					return 1;
				}
				
			};
			
			coletaTask.execute(1);
			
			
		}
	};

//In the same activity you’ll need the following to retrieve the results:
public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	if (requestCode == 0) {

		if (resultCode == RESULT_OK) {
		
			tombamento.setText(intent.getStringExtra("SCAN_RESULT"));
		} else if (resultCode == RESULT_CANCELED) {
			
			tombamento.setText("Scan cancelled.");
		}
	}
}

}
