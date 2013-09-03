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

import java.io.Serializable;

/**
 * Fonte financeira ou beneficente de aquisição de um bem permanente. Um bem
 * pode ser adquirido com recursos próprios da UFG, doado ou transferido.
 * Exemplos: UFG, FUNDEP, CNPQ, Doação.
 * 
 * @author Emerson Jose Porfirio
 */
public class Origem implements Serializable, Cloneable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 7859647779471307005L;

	/**
	 * Id da origem
	 */
	private Long id;

	/**
	 * Resumo da origem
	 */
	private String resumo;

	/**
	 * Detalhe da origem
	 */
	private String detalhe;

	/**
	 * Construtor da classe
	 */
	public Origem() {
	}

	/**
	 * Construtor de uma origem
	 * 
	 * @param resumo
	 *            Resumo da origem
	 * 
	 * @param detalhe
	 *            Detalhe de uma origem
	 */
	public Origem(String resumo, String detalhe) {
		setResumo(resumo);
		setDetalhe(detalhe);
	}

	/**
	 * Obtem o id da origem
	 * 
	 * @return Id da origem
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id da origem
	 * 
	 * @param idOrigem
	 *            id da origem
	 */
	public void setId(Long idOrigem) {
		this.id = idOrigem;
	}

	/**
	 * Texto resumido em Observações que caracteriza a origem de um bem
	 * permanente.
	 */
	public String getResumo() {
		return resumo;
	}

	/**
	 * Define o resumo da origem
	 * 
	 * @param resumo
	 *            Resumo da origem
	 */
	private void setResumo(String resumo) {
		final int limiteResumo = 40;
		if (resumo == null || resumo.trim().length() == 0
				|| resumo.length() > limiteResumo) {
			throw new IllegalArgumentException(String.format(
					"Valor inválido para o atributo resumo: '%s'.", resumo));
		}
		this.resumo = resumo;
	}

	/**
	 * Texto detalhado em Observações que relata detalhes sobre a origem de um
	 * bem permanente.
	 * 
	 * @return
	 */
	public String getDetalhe() {
		return detalhe;
	}

	/**
	 * Define o detalhe da origem
	 * 
	 * @param detalhe
	 *            Detalhe da origem
	 */
	private void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	/**
	 * Compara a origem
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Origem)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Origem outro = (Origem) obj;
		return this.resumo.equals(outro.resumo);
	}

	/**
	 * HashCode da origem
	 */
	public int hashCode() {
		final int valorInicial = 1;
		final int valorIncremental = 31;

		int hash = valorInicial;
		hash = hash * valorIncremental
				+ (resumo.isEmpty() ? 0 : resumo.hashCode());
		hash = hash * valorIncremental
				+ (detalhe.isEmpty() ? 0 : detalhe.hashCode());
		return hash;
	}

	@Override
	public Origem clone() throws CloneNotSupportedException {
		return (Origem) super.clone();
	}
}
