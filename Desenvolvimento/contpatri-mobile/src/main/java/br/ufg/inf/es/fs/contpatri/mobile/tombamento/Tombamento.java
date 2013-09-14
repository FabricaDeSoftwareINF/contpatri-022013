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

/**
 * Classe que abstrai o Tombamento. É nela que é feita as operações de envio
 * para o WebService, coleta de informações e exibição de informações.
 * 
 * @author Rogério Tristão Junior
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ codigo >>> 32);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Tombamento other = (Tombamento) obj;
		if (codigo != other.codigo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Tombamento [codigo=" + codigo + ", situacao=" + situacao
				+ ", sublocal=" + sublocal + ", observacao=" + observacao
				+ ", ultimaAlteracao=" + ultimaAlteracao + "]";
	}

	private String observacao;
	private long ultimaAlteracao;

	/**
	 * Construtor padrão.
	 */
	public Tombamento() {

	}

	/**
	 * Construtor para instanciar o objeto do tipo <code>Tombamento</code> já
	 * com seus valores.
	 * 
	 * @param id
	 *            código do tombamento
	 * @param sit
	 *            situação do tombamento
	 * @param subLocal
	 *            sublocal do tombamento
	 * @param obs
	 *            observação do tombamento
	 */
	public Tombamento(final long id, final String sit, final String subLocal,
			final String obs) {
		codigo = id;
		situacao = sit;
		sublocal = subLocal;
		observacao = obs;
		setUltimaAlteracao();
	}

	/**
	 * Construtor utilizado para o <code>Parcel</code>.
	 * 
	 * @param source
	 *            leitor de dados <code>Parcel</code>
	 */
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
		ultimaAlteracao = new Timestamp(System.currentTimeMillis()).getTime();
	}

	public void setUltimaAlteracao(final long alteracao) {
		ultimaAlteracao = alteracao;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(final Parcel paramParcel, final int paramInt) {
		paramParcel.writeLong(codigo);
		paramParcel.writeString(situacao);
		paramParcel.writeString(sublocal);
		paramParcel.writeString(observacao);
		paramParcel.writeLong(ultimaAlteracao);
	}

	public static final Parcelable.Creator<Tombamento> CREATOR = new Parcelable.Creator<Tombamento>() {

		@Override
		public Tombamento createFromParcel(final Parcel source) {
			return new Tombamento(source);
		}

		@Override
		public Tombamento[] newArray(final int size) {
			return new Tombamento[size];
		}
	};

}
