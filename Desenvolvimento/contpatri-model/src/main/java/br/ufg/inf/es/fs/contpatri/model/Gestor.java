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
 * Identifica o Gestor de Patrimônio responsável pela gestão dos bens
 * patrimoniais da Unidade da UFG.
 * 
 * @author Emerson Jose Porfirio
 */
public class Gestor extends Pessoa implements Serializable, Cloneable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3415217048887883532L;

	/**
	 * Construtor da classe
	 */
	public Gestor() {
	}

	/**
	 * Construtor da classe gestor
	 * 
	 * @param nome
	 *            Nome do gestor
	 * 
	 * @param matricula
	 *            Matricula do gestor
	 * 
	 * @param email
	 *            Email do gestor
	 */
	public Gestor(String nome, String matricula, String email) {
		setNome(nome);
		setMatricula(matricula);
		setEmail(email);
	}

	/**
	 * Compara dois gestores
	 */
	@Override
	public boolean equals(Object outro) {
		if (outro == null) {
			return false;
		}
		if (!(outro instanceof Gestor)) {
			return false;
		}
		if (this == outro) {
			return true;
		}
		Gestor outroGestor = (Gestor) outro;
		return !((this.getMatricula().compareTo(outroGestor.getMatricula()) != 0) || (this
				.getNome().compareTo(outroGestor.getNome()) != 0));
	}

	/**
	 * HashCode de um Gestor
	 */
	public int hashCode() {
		final int valorInicial = 1;
		final int valorIncremental = 31;

		int hash = valorInicial;
		hash = hash * valorIncremental
				+ (getNome().isEmpty() ? 0 : getNome().hashCode());
		hash = hash * valorIncremental
				+ (getMatricula().isEmpty() ? 0 : getMatricula().hashCode());
		return hash;
	}

	/**
	 * Clona um gestor
	 */
	public Gestor clone() throws CloneNotSupportedException {
		return (Gestor) super.clone();
	}
}
