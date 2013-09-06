package br.ufg.inf.es.fs.contpatri.mobile.modelo;

import java.sql.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe modelo do tombamento.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class Tombamento {

	public static final transient String CODIGO = "idTombamento";
	public static final transient String ULTALTERACAO = "ultimaAlteracao";
	public static final transient String SITUACAO = "situacao";
	public static final transient String SUBLOCAL = "subLocal";
	public static final transient String OBSERVACAO = "observacao";
	public static final transient String DESCRICAO = "descricao";

	private transient JSONObject json;

	public Tombamento(final String jsonString) throws JSONException {
		this(new JSONObject(jsonString));
	}

	public Tombamento(final JSONObject jsonObj) throws JSONException {
		json = jsonObj;
	}

	public String getCodigo() throws JSONException {
		return json.getString(CODIGO);
	}

	public void setCodigo(final String idTombamento) throws JSONException {
		json.put(Tombamento.CODIGO, idTombamento);
	}

	public String getDescricao() throws JSONException {
		return json.getString(DESCRICAO);
	}

	public void setDescricao(final String descricao) throws JSONException {
		json.put(Tombamento.DESCRICAO, descricao);
	}

	public String getSituacao() throws JSONException {
		return json.getString(SITUACAO);
	}

	public void setSituacao(final String situacao) throws JSONException {
		json.put(Tombamento.SITUACAO, situacao);
	}

	public String getSubLocal() throws JSONException {
		return json.getString(SUBLOCAL);
	}

	public void setSubLocal(final String subLocal) throws JSONException {
		json.put(Tombamento.SUBLOCAL, subLocal);
	}

	public String getObservacao() throws JSONException {
		return json.getString(OBSERVACAO);
	}

	public void setObservacao(final String observacao) throws JSONException {
		json.put(Tombamento.OBSERVACAO, observacao);
	}

	public String getUltimaAlteracao() throws JSONException {
		return json.getString(ULTALTERACAO);
	}
	
	public void registraTombamento() throws JSONException {
		json.put(Tombamento.OBSERVACAO, getTimestamp().toString());
	}

	private Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public void setUltimaAlteracao(final String ultimaAlteracao)
			throws JSONException {
		json.put(ULTALTERACAO, getTimestamp());
	}

}
