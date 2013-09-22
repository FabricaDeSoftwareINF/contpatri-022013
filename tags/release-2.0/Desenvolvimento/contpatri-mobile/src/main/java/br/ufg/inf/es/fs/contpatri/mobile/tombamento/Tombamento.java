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

	private long codigo;
	private String situacao;
	private String sublocal;
	private String observacao;
	private long ultimaAlteracao;

	/**
	 * Método utilizado pela interface Parcelable para ser possível ler os dados
	 * da classe <code>Tombamento</code> e repassar para <code>Adapter</code>,
	 * <code>List</code> ou outras que utilizem a interface Parcelable para
	 * comunicação ou usos afins.
	 */
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

	@Override
	public int describeContents() {
		return 0;
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

	/**
	 * 
	 * @return o código do tombamento
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * 
	 * @return a observação do tombamento
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * 
	 * @return a situação do tombamento
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * 
	 * @return o sublocal do tombamento
	 */
	public String getSublocal() {
		return sublocal;
	}

	/**
	 * 
	 * @return a última alteração do tombamento
	 */
	public long getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ codigo >>> 32);
		return result;
	}

	/**
	 * Atribui um código ao tombamento.
	 * 
	 * @param codigo
	 *            novo código
	 */
	public void setCodigo(final long codigo) {
		this.codigo = codigo;
	}

	/**
	 * Atribui uma nova observação ao tombamento.
	 * 
	 * @param observacao
	 *            nova observação
	 */
	public void setObservacao(final String observacao) {
		this.observacao = observacao;
	}

	/**
	 * Atribui uma nova situação ao tombamento.
	 * 
	 * @param situacao
	 *            nova situação
	 */
	public void setSituacao(final String situacao) {
		this.situacao = situacao;
	}

	/**
	 * Atribui um novo sublocal ao tombamento.
	 * 
	 * @param sublocal
	 *            novo sublocal
	 */
	public void setSublocal(final String sublocal) {
		this.sublocal = sublocal;
	}

	/**
	 * Define a última alteração do tombamento.
	 */
	public void setUltimaAlteracao() {
		ultimaAlteracao = new Timestamp(System.currentTimeMillis()).getTime();
	}

	/**
	 * Atribui a data da última alteração do tombamento.
	 * 
	 * @param alteracao
	 *            nova data da última alteração
	 */
	public void setUltimaAlteracao(final long alteracao) {
		ultimaAlteracao = alteracao;
	}

	@Override
	public String toString() {
		return "Tombamento [codigo=" + codigo + ", situacao=" + situacao
				+ ", sublocal=" + sublocal + ", observacao=" + observacao
				+ ", ultimaAlteracao=" + ultimaAlteracao + "]";
	}

	@Override
	public void writeToParcel(final Parcel paramParcel, final int paramInt) {
		paramParcel.writeLong(codigo);
		paramParcel.writeString(situacao);
		paramParcel.writeString(sublocal);
		paramParcel.writeString(observacao);
		paramParcel.writeLong(ultimaAlteracao);
	}

}
