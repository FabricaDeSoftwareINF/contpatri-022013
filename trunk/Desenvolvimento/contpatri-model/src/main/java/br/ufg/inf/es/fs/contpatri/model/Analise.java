/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ Instituto de
 * Informática UFG: http://www.inf.ufg.br Projeto CONTPATRI DROPBOX:
 * https://www.dropbox.com/home/CONTPATRI%20-%20012013 Projeto CONTPATRI
 * REDMINE:
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
package br.ufg.inf.es.fs.contpatri.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

/**
 * Resultado da analise de um determinado bem patrimonial realizado dentro de um
 * inventario.
 * 
 * @author Emerson Jose Porfirio
 */
public class Analise implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8538872437513272042L;

	/**
	 * Identificador interno
	 */
	private Long id;

	/**
	 * Bem patrimonial
	 */
        @Expose
	private BemPatrimonial bemPatrimonial;

	/**
	 * Inventario do bem
	 */
	private Inventario inventario;

	/**
	 * Situacao do bem
	 */
        @Expose
	private TipoSituacao situacao;

	/**
	 * Construtor da classe
	 */
	public Analise() {
	}

	/**
	 * Construtor da classe de analise
	 * 
	 * @param bemPatrimonial
	 *            Bem patrimonial
	 * 
	 * @param inventario
	 *            Inventario do bem
	 * 
	 * @param situacao
	 *            Situacao do bem
	 */
	public Analise(BemPatrimonial bemPatrimonial, Inventario inventario,
			TipoSituacao situacao) {
		setBemPatrimonial(bemPatrimonial);
		setInventario(inventario);
		setSituacao(situacao);
	}

	/**
	 * Obtem o id
	 * 
	 * @return Id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id
	 * 
	 * @param id
	 *            Id
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
	 * Define o bem patrimonial
	 * 
	 * @param bemPatrimonial
	 *            Bem patrimonial
	 */
	public void setBemPatrimonial(BemPatrimonial bemPatrimonial) {
		if (bemPatrimonial == null) {
			throw new IllegalArgumentException("O bem não pode ser nulo");
		}
		this.bemPatrimonial = bemPatrimonial;
	}

	/**
	 * Obtem o inventario
	 * 
	 * @return Inventario
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * Define o inventario
	 * 
	 * @param inventario
	 *            Inventario
	 */
	public void setInventario(Inventario inventario) {
		if (inventario == null) {
			throw new IllegalArgumentException("O inventário não pode ser nulo");
		}
		this.inventario = inventario;
	}

	/**
	 * Obtem a situacao do bem
	 * 
	 * @return Situacao do bem
	 */
	public TipoSituacao getSituacao() {
		return situacao;
	}

	/**
	 * Define a situacao do bem
	 * 
	 * @param situacao
	 *            Situacao do bem
	 */
	public void setSituacao(TipoSituacao situacao) {
		this.situacao = situacao;
	}

	/**
	 * Compara duas analises
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Analise analisa = (Analise) o;
		if (!bemPatrimonial.equals(analisa.bemPatrimonial)) {
			return false;
		}
		if (!inventario.equals(analisa.inventario)) {
			return false;
		}
		return true;
	}

	/**
	 * HashCode da analise
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
