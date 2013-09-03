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
 * Espaço físico onde um ou mais bens permanentes são usualmente dispostos, seja
 * para uso ou para estocagem. Exemplo: Laboratório, Sala de Aulas, etc.
 * 
 * @author Emerson Jose Porfirio
 */
@SuppressWarnings("serial")
public class SubLocal implements Serializable, Cloneable {

	/**
	 * Id de um sublocal
	 */
	private Long id;

	/**
	 * Nome de um sublocal
	 */
	private String nome;

	/**
	 * Construtor da classe
	 */
	public SubLocal() {
	}

	/**
	 * Construtor de um sublocal
	 * 
	 * @param nome
	 *            Nome do sublocal
	 */
	public SubLocal(String nome) {
		setNome(nome);
	}

	/**
	 * Obtem o id do sublocal
	 * 
	 * @return Id do sublocal
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o id do sublocal
	 * 
	 * @param id
	 *            Id do sublocal
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Define o nome do sublocal
	 * 
	 * @param nome
	 *            Nome do sublocal
	 */
	private void setNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException(
					"Campo nome não pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	/**
	 * Obtem o nome do sublocal
	 * 
	 * @return Nome do sublocal
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Clona um sublocal
	 */
	@Override
	public SubLocal clone() throws CloneNotSupportedException {
		return (SubLocal) super.clone();
	}

	/**
	 * Compara dois sublocais
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SubLocal)) {
			return false;
		}
		SubLocal outro = (SubLocal) obj;
		return this.nome.equals(outro.nome);
	}

	/**
	 * HashCode do sublocal
	 */
	@Override
	public int hashCode() {
		final int valorInicial = 7;
		final int valorIncremental = 23;

		int hash = valorInicial;
		hash = valorIncremental * hash
				+ (getId() != null ? getId().hashCode() : 0);
		return hash;
	}
}
