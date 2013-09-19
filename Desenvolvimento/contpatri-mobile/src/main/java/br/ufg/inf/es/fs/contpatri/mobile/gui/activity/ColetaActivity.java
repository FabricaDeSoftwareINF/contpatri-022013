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

import java.util.Map.Entry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.barcodescan.IntentIntegrator;
import br.ufg.inf.es.fs.contpatri.mobile.barcodescan.IntentResult;
import br.ufg.inf.es.fs.contpatri.mobile.controller.ColetaController;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.Conversores;

/**
 * Tela para coleta das informações do ativo fixo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ColetaActivity extends Activity {

	private ColetaController coleta;

	private EditText edtCodigo;
	private EditText sublocal;
	private EditText observacao;
	private EditText alteracao;

	private Spinner spinner;

	private String selecionado;

	private Tombamento tmb;

	/**
	 * Classe que confirma a ação para gravar o objeto <code>Tombamento</code>
	 * em um arquivo <b>JSON</b>.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void confirmar(final View view) {

		final String codigo = edtCodigo.getText().toString();
		final String sub = sublocal.getText().toString();
		final String obs = observacao.getText().toString();

		/*
		 * Se os campos forem nulos, inválidos, mostrará uma caixa de diálogo
		 * informando o erro, senão gravará o objeto tombamento em um arquivo.
		 */
		if (codigo.length() == 0 || sub.length() == 0 || obs.length() == 0) {

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
			coleta = new ColetaController(this);
			coleta.gerarColeta(tmb);

		}

	}

	@Override
	public void onActivityResult(final int request, final int result,
			final Intent i) {

		final IntentResult resultadoScan = IntentIntegrator
				.parseActivityResult(request, result, i);

		if (resultadoScan == null) {
			Toast.makeText(this, R.string.scan_cancelado, Toast.LENGTH_LONG)
					.show();
		} else {

			Entry<Boolean, Tombamento> valores = coleta
					.verificaResultadoScan(this, i).entrySet().iterator()
					.next();
			tmb = valores.getValue();

			/*
			 * Se for verdadeiro, irá recuperar os dados do mesmo, caso
			 * contrário irá inserir um novo registro (se o objeto tombamento
			 * vier com o códig), caso contrário é um QRCode inválido.
			 */
			if (valores.getKey()) {
				recuperaDados();
			} else {
				if (tmb == null) {
					edtCodigo.setText(String.valueOf(0));
				} else {
					edtCodigo.setText(String.valueOf(tmb.getCodigo()));
				}

			}
		}

	}

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_coleta);

		tmb = getIntent().getParcelableExtra("tombamento");

		edtCodigo = (EditText) findViewById(R.id.edtTombamento);
		sublocal = (EditText) findViewById(R.id.edtSubLocal);
		observacao = (EditText) findViewById(R.id.edtObservacao);
		final Button qrCode = (Button) findViewById(R.id.btnScanQrCode);

		alteracao = (EditText) findViewById(R.id.edtUltimaAlteracao);
		alteracao.setEnabled(false);
		spinner = (Spinner) findViewById(R.id.spnSituacao);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(final AdapterView<?> parent,
					final View view, final int pos, final long id) {
				selecionado = (String) parent.getItemAtPosition(pos);
			}

			@Override
			public void onNothingSelected(final AdapterView<?> parent) {

			}
		});

		/*
		 * Se a variável tmb for nula, quer dizer que é uma inclusão de um novo
		 * ativo fixo, caso contrário ele estará abrindo um registro de ativo
		 * fixo.
		 */
		if (tmb != null) {

			edtCodigo.setText(String.valueOf(tmb.getCodigo()));
			edtCodigo.setEnabled(false);

			sublocal.setText(tmb.getSublocal());

			observacao.setText(tmb.getObservacao());
			tmb.setUltimaAlteracao();

			qrCode.setEnabled(false);

			final String[] arraySituacao = getResources().getStringArray(
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

	/**
	 * Método que recupera dos dados quando é selecionado um código de
	 * tombamento que já existe na base de dados.
	 */
	private void recuperaDados() {

		edtCodigo.setText(String.valueOf(tmb.getCodigo()));
		sublocal.setText(tmb.getSublocal());
		observacao.setText(tmb.getObservacao());
		alteracao.setText(String.valueOf(tmb.getUltimaAlteracao()));

		final String[] arraySituacao = getResources().getStringArray(
				R.array.nomes_array);

		/*
		 * Atribuição do spinner conforme gravado no banco de dados.
		 */
		if (tmb.getSituacao().equals(arraySituacao[0])) {
			spinner.setSelection(0);
		} else if (tmb.getSituacao().equals(arraySituacao[1])) {
			spinner.setSelection(1);
		} else if (tmb.getSituacao().equals(arraySituacao[2])) {
			spinner.setSelection(2);
		}

	}

	/**
	 * Classe que chama o aplicativo BarcodeScan para ler o QRCode.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void scanQRCode(final View view) {
		final IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
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

}
