package br.ufg.inf.es.fs.contpatri.mobile.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.FolderManager;

public final class ColetaControler {
	
	private transient final JSONArray jsonArray;
	private transient String jsonUrl;
	public JSONObject tombamentoJSON = new JSONObject();
	private Tombamento tombamento;

	public ColetaControler(final String jsonUrl) throws JSONException {
		this.jsonUrl = jsonUrl;
		this.jsonArray = new JSONArray(FolderManager.callURL(jsonUrl));
	}

	public synchronized boolean buscaPatrimonio(final String tombamento) {

		int count = jsonArray.length();

		for (int i = 0; i < count; i++) {

			try {
				if (jsonArray.getJSONObject(i).getString(Tombamento.CODIGO)
						.equals(tombamento)) {
					this.tombamento = new Tombamento(jsonArray.getJSONObject(i));
					return true;
				}
			} catch (final JSONException e) {
				Log.e(ColetaControler.class.getSimpleName(), "", e);
			}
		}
		return false;
	}

	public boolean finalizarColeta(final String situacao, final String observacao) {

		try {
			
			tombamento.setSituacao(situacao);
			tombamento.setObservacao(observacao);
			tombamento.registraTombamentoUltimaAlteracao();
			
		} catch (final JSONException e) {
			Log.e(ColetaControler.class.getSimpleName(), "", e);
		}

		File output = new File(jsonUrl);

		if (output.canWrite()) {
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(output, false));

				out.write(jsonArray.toString());

			} catch (final IOException e) {
				Log.e(ColetaControler.class.getSimpleName(), "", e);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						Log.e(ColetaControler.class.getSimpleName(), "", e);
					}
				}
			}
		}
		return false;
	}

	public Tombamento getTombamento() {
		return tombamento;
	}

	public void setTombamento(final Tombamento tmbmnt) {
		tombamento = tmbmnt;
	}

}
