package br.ufg.inf.fs.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufg.inf.fs.model.Tombamento;

public class ColetaControler {

	/**
	 * @param args
	 */
	private String jsonUrl;
	private final String idTombamento = "idTombamento";
	public JSONObject tombamentoJSON = new JSONObject();
	private Tombamento tombamento;
	JSONArray jsonArray;

	public ColetaControler(String jsonUrl) throws JSONException {
		this.jsonUrl = jsonUrl;

		this.jsonArray = new JSONArray(FolderManager.callURL(jsonUrl));

	}

	public synchronized boolean buscaPatrimonio(String tombamento)
			throws JSONException {

		int count = jsonArray.length();

		for (int i = 0; i < count; i++) {

			if (jsonArray.getJSONObject(i).getString(idTombamento)
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
		tombamento.registraTombamentoUltimaAlteracao();

		File output = new File(jsonUrl);

		if (output.canWrite()) {
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter(output, false));

				out.write(jsonArray.toString());

			} catch (IOException e) {
				// Do what you want here, print a message or something
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						// Again, do what you want here
					}
				}
			}

		}

		return false;

	}

	public Tombamento getTombamento() {
		return tombamento;
	}

	public void setTombamento(Tombamento tombamento) {
		this.tombamento = tombamento;
	}

}
