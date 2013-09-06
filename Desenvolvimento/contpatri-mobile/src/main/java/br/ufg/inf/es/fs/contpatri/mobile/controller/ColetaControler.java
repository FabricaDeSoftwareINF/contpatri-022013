package br.ufg.inf.es.fs.contpatri.mobile.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.modelo.Tombamento;

public class ColetaControler {

	private transient final String jsonUrl;
	private transient final JSONObject json;
	private transient final JSONArray jsonArray;
	private transient Tombamento tombamento;

	public ColetaControler(String jsonUrl) throws JSONException {
		this.jsonUrl = jsonUrl;
		this.jsonArray = new JSONArray(FolderManager.callURL(jsonUrl));
		json = new JSONObject();
	}

	public synchronized boolean buscaPatrimonio(String tombamento)
			throws JSONException {

		int count = jsonArray.length();

		for (int i = 0; i < count; i++) {

			if (jsonArray.getJSONObject(i).getString(Tombamento.CODIGO)
					.equals(tombamento)) {
				this.tombamento = new Tombamento(jsonArray.getJSONObject(i));
				return true;
			}
		}

		return false;
	}

	public boolean finalizarColeta(String situacao, String observacao)
			throws JSONException {

		tombamento.setSituacao(situacao);
		tombamento.setObservacao(observacao);
		tombamento.registraTombamento();

		File output = new File(jsonUrl);

		if (output.canWrite()) {
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(output, false));

				out.write(jsonArray.toString());

			} catch (final IOException excecao) {
				Log.e(ColetaControler.class.getSimpleName(), "", excecao);
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (final IOException excecao) {
						Log.e(ColetaControler.class.getSimpleName(), "", excecao);
					}
				}
			}

		}

		return false;

	}



}
