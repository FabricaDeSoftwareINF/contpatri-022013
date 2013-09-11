package br.ufg.inf.es.fs.contpatri.mobile.tombamento;

import org.json.JSONException;
import org.json.JSONObject;

public final class Tombamento {

	public static transient final String CODIGO = "idTombamento";
	public static transient final String ULTIMA_ALTERACAO = "ultimaAlteracao";
	public static transient final String SITUACAO = "situacao";
	public static transient final String SUBLOCAL = "subLocal";
	public static transient final String OBSERVACAO = "observacao";
	public static transient final String DESCRICAO = "descricao";
	
	private JSONObject json = new JSONObject();

	public Tombamento(String jsonString) throws JSONException {
		this.json = new JSONObject(jsonString);
	}

	public Tombamento(JSONObject jsonObj) throws JSONException {
		this.json = jsonObj;
	}

	private String obterTimeStamp() {
		Long tsLong = System.currentTimeMillis() / 1000;
		return tsLong.toString();
	}

	public String getIdTombamento() throws JSONException {
		return json.getString(CODIGO);
	}

	public void setIdTombamento(String idTombamento) throws JSONException {
		json.put(CODIGO, idTombamento);
	}

	public String getDescricao() throws JSONException {
		return json.getString(DESCRICAO);
	}

	public void setDescricao(String descricao) throws JSONException {
		json.put(DESCRICAO, descricao);
	}

	public String getSituacao() throws JSONException {
		return json.getString(SITUACAO);
	}

	public void setSituacao(String situacao) throws JSONException {
		json.put(SITUACAO, situacao);
	}

	public String getSubLocal() throws JSONException {
		return json.getString(SUBLOCAL);
	}

	public void setSubLocal(String subLocal) throws JSONException {
		json.put(SUBLOCAL, subLocal);
	}

	public String getObservacao() throws JSONException {
		return json.getString(OBSERVACAO);
	}

	public void setObservacao(String observacao) throws JSONException {
		json.put(OBSERVACAO, observacao);
	}

	public String getTombamentoUltimaAlteracao() throws JSONException {
		return json.getString(ULTIMA_ALTERACAO);
	}

	public void setTombamentoUltimaAlteracao(String ultimaAlteracao)
			throws JSONException {
		json.put(ULTIMA_ALTERACAO, ultimaAlteracao);
	}

	public void registraTombamentoUltimaAlteracao() throws JSONException {
		json.put(ULTIMA_ALTERACAO, obterTimeStamp());

	}

}
