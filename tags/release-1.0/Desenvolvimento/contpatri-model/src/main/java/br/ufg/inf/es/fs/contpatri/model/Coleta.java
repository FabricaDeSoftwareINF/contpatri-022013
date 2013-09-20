/**
 * Esse documento é parte do código fonte e artefatos relacionados 
 * ao projeto CONTPATRI, em desenvolvimento pela Fábrica de Software
 * da UFG.
 * 
 *  Links relevantes:
 *  Fábrica de Software: http://fs.inf.ufg.br/
 *  Instituto de Informática UFG: http://www.inf.ufg.br
 *  Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013
 *  Projeto CONTPATRI REDMINE: 
 *
 * Copyleft © UFG.
 * 
 * Licenciado sobre a licença GNU-GPL v3
 *
 * Você pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html
 * 
 * A menos que especificado ou exigido por legislação local, o software é 
 * fornecido "da maneira que está", sem garantias ou condições de qualquer 
 * tipo, nem expressas nem implícitas. Em caso de dúvidas referir a licença GNU-GPL.
 */

package br.ufg.inf.es.fs.contpatri.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

/**
 * Resultado da conferência de um determinado bem patrimonial realizado dentro
 * de um inventário
 */
public class Coleta implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -8538872437513272042L;


	/**
	 * Id da coleta
	 */
	private Long id;

	/**
	 * Bem patrimonial da coleta
	 */
        @Expose
	private BemPatrimonial bemPatrimonial;

	/**
	 * Inventario da coleta
	 */
	private Inventario inventario;

	/**
	 * Situacao da coleta
	 */
        @Expose
	private TipoSituacao situacao;

	/**
	 * Construtor da coleta
	 */
	public Coleta() {
	}

	/**
	 * Construtor da coleta
	 * 
	 * @param bemPatrimonial
	 *            Bem patrimonial da coleta
	 * @param inventario
	 *            Inventario da coleta
	 * @param situacao
	 *            Situacao da coleta
	 */
	public Coleta(BemPatrimonial bemPatrimonial, Inventario inventario,
			TipoSituacao situacao) {
		setBemPatrimonial(bemPatrimonial);
		setInventario(inventario);
		setSituacao(situacao);
	}

	/**
	 * Obtem a id
	 * 
	 * @return Id da coleta
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id
	 * 
	 * @param id
	 *            Id da coleta
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtem o bem patrimonial
	 * 
	 * @return Bem patrimonial
	 */
	public BemPatrimonial getBemPatrimonial() {
		return bemPatrimonial;
	}

	/**
	 * Define o bem patrimonial da coleta
	 * 
	 * @param bemPatrimonial
	 *            Bem patrimonial da coleta
	 */
	public final void setBemPatrimonial(BemPatrimonial bemPatrimonial) {
		if (bemPatrimonial == null) {
			throw new IllegalArgumentException("O bem não pode ser nulo");
		}
		this.bemPatrimonial = bemPatrimonial;
	}

	/**
	 * Obtem o inventario da coleta
	 * 
	 * @return Inventario da coleta
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * Define o inventario da coleta
	 * 
	 * @param inventario
	 *            Inventario da coleta
	 */
	public final void setInventario(Inventario inventario) {
		if (inventario == null) {
			throw new IllegalArgumentException("O inventário não pode ser nulo");
		}
		this.inventario = inventario;
	}

	/**
	 * Obtem a situacao da coleta
	 * 
	 * @return Situacao da coleta
	 * 
	 */
	public TipoSituacao getSituacao() {
		return situacao;
	}

	/**
	 * Define a situacao da coleta
	 * 
	 * @param situacao
	 *            Situacao da coleta
	 */
	public final void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao;
	}

	/**
	 * Compara duas coletas
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Coleta confere = (Coleta) o;
		if (!bemPatrimonial.equals(confere.bemPatrimonial)) {
			return false;
		}
		if (!inventario.equals(confere.inventario)) {
			return false;
		}
		return true;
	}

	/**
	 * HashCode da coleta
	 */
	@Override
	public int hashCode() {
		final int valorIncremental = 31;
		int result = bemPatrimonial != null ? bemPatrimonial.hashCode() : 0;
		result = valorIncremental * result
				+ (inventario != null ? inventario.hashCode() : 0);
		return result;
	}

}
