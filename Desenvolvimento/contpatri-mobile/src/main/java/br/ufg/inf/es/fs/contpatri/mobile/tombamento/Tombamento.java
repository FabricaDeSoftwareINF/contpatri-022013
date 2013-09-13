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
package br.ufg.inf.es.fs.contpatri.mobile.tombamento;

import java.sql.Timestamp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Classe que abstrai o Tombamento. É nela que é feita as operações de envio
 * para o WebService, coleta de informações e exibição de informações.
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza
 * @author Fellipe Cesar
 * @author Thiago Fernandes
 * 
 */
public final class Tombamento implements Parcelable {

	public static final String CODIGO = "codigo";
	public static final String SITUACAO = "situacao";
	public static final String SUBLOCAL = "subLocal";
	public static final String ULTIMA_ALTERACAO = "timestamp";
	public static final String OBSERVACAO = "observacao";

	private long codigo;
	private String situacao;
	private String sublocal;
	private String observacao;
	private long ultimaAlteracao;

	public Tombamento() {

	}

	public Tombamento(final long id, final String sit, final String subLocal,
			final String obs) {
		codigo = id;
		situacao = sit;
		sublocal = subLocal;
		observacao = obs;
		setUltimaAlteracao();
	}

	public Tombamento(final Parcel source) {
		codigo = source.readLong();
		situacao = source.readString();
		sublocal = source.readString();
		observacao = source.readString();
		ultimaAlteracao = source.readLong();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(final long codigo) {
		this.codigo = codigo;
	}

	public long getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao() {
		this.ultimaAlteracao = new Timestamp(System.currentTimeMillis())
				.getTime();
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(final String situacao) {
		this.situacao = situacao;
	}

	public String getSublocal() {
		return sublocal;
	}

	public void setSublocal(final String sublocal) {
		this.sublocal = sublocal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	public void fromJson(final String json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Tombamento tmp = (Tombamento) gson.fromJson(json, Tombamento.class);
		codigo = tmp.getCodigo();
		situacao = tmp.getSituacao();
		sublocal = tmp.getSublocal();
		ultimaAlteracao = tmp.getUltimaAlteracao();
		observacao = tmp.getObservacao();
	}

	
	public int describeContents() {
		return 0;
	}

	
	public void writeToParcel(final Parcel paramParcel, final int paramInt) {
		paramParcel.writeLong(codigo);
		paramParcel.writeString(situacao);
		paramParcel.writeString(sublocal);
		paramParcel.writeString(observacao);
		paramParcel.writeLong(ultimaAlteracao);
	}

	public static final Parcelable.Creator<Tombamento> CREATOR = new Parcelable.Creator<Tombamento>() {
		
		public Tombamento createFromParcel(final Parcel source) {
			return new Tombamento(source);
		}

		
		public Tombamento[] newArray(final int size) {
			return new Tombamento[size];
		}
	};

}
