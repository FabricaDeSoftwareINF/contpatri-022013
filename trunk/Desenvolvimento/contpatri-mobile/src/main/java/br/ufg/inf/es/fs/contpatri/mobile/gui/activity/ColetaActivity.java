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
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.barcodescan.IntentIntegrator;
import br.ufg.inf.es.fs.contpatri.mobile.barcodescan.IntentResult;
import br.ufg.inf.es.fs.contpatri.mobile.controller.ColetaControler;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.Conversores;

public final class ColetaActivity extends Activity {

	private transient EditText tombamento;
	private transient EditText sublocal;
	private transient EditText observacao;
	private transient EditText alteracao;

	private transient ColetaControler coleta;

	private transient Spinner spinner;

	private transient String selecionado;

	private transient Tombamento tmb;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_coleta);

		tmb = getIntent().getParcelableExtra("tombamento");

		tombamento = (EditText) findViewById(R.id.edtTombamento);
		sublocal = (EditText) findViewById(R.id.edtSubLocal);
		observacao = (EditText) findViewById(R.id.edtObservacao);
		alteracao = (EditText) findViewById(R.id.edtUltimaAlteracao);
		alteracao.setEnabled(false);
		spinner = (Spinner) findViewById(R.id.spnSituacao);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			public void onItemSelected(final AdapterView<?> parent,
					final View view, final int pos, final long id) {
				selecionado = (String) parent.getItemAtPosition(pos);
			}

			
			public void onNothingSelected(final AdapterView<?> parent) {

			}
		});

		if (tmb != null) {

			Button btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
			btnConfirmar.setClickable(false);

			tombamento.setText(String.valueOf(tmb.getCodigo()));
			sublocal.setText(tmb.getSublocal());
			observacao.setText(tmb.getObservacao());

			String[] arraySituacao = getResources().getStringArray(
					R.array.nomes_array);

			if (tmb.getSituacao().equals(arraySituacao[0])) {
				spinner.setSelection(0);
			} else if (tmb.getSituacao().equals(arraySituacao[1])) {
				spinner.setSelection(1);
			} else if (tmb.getSituacao().equals(arraySituacao[2])) {
				spinner.setSelection(2);
			}

		} else {
			tmb = new Tombamento();
			tmb.setUltimaAlteracao();
		}

		alteracao.setText(Conversores.longToDate(tmb.getUltimaAlteracao()));

	}

	public void scanQRCode(final View view) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}

	public void confirmar(final View view) {

		String codigo = tombamento.getText().toString();
		String sub = sublocal.getText().toString();
		String obs = observacao.getText().toString();

		if ((codigo.isEmpty() || codigo == null)
				|| (sub.isEmpty() || sub == null)
				|| (obs.isEmpty() || obs == null)) {
			AlertDialog.Builder builder;
			builder = new AlertDialog.Builder(this);
			builder.setIcon(android.R.drawable.ic_dialog_alert);
			builder.setTitle("Erro");
			builder.setMessage("É necessário preencher todos os campos!");
			final AlertDialog dialog = builder.create();
			dialog.setCanceledOnTouchOutside(true);
			dialog.show();
		} else {
			tmb.setCodigo(Long.parseLong(codigo));
			tmb.setSituacao(selecionado);
			tmb.setSublocal(sub);
			tmb.setObservacao(obs);
			coleta = new ColetaControler();
			coleta.gerarColeta(this, tmb);
		}

	}

	@Override
	public void onActivityResult(final int request, final int result,
			final Intent i) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(request,
				result, i);
		if (scanResult.getRawBytes() != null) {
			tombamento.setText(i.getStringExtra("SCAN_RESULT"));
		} else {
			Toast.makeText(this, "Cancelado o escaneamento", Toast.LENGTH_LONG)
					.show();
		}
	}

}
