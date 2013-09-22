/**
  * Esse documento e parte do código fonte e artefatos relacionados 
  * ao projeto CONTPATRI, em desenvolvimento pela Fabrica de Software
  * da UFG.
  * 
  *  Links relevantes:
  *  Fabrica de Software: http://fs.inf.ufg.br/
  *  Instituto de Informatica UFG: http://www.inf.ufg.br
  *  Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013
  *  Projeto CONTPATRI REDMINE: http://fs.inf.ufg.br/redmine/projects/contpatri-012013-
  *
  * Copyleft UFG.
  * 
  * Licenciado sobre a licenca GNU-GPL v3
  *
  * Voce pode obter uma copia da licenca em http://www.gnu.org/licenses/gpl.html
  * 
  * A menos que especificado ou exigido por legislacao local, o software e 
  * fornecido "da maneira que esta", sem garantias ou condições de qualquer 
  * tipo, nem expressas nem implicitas. Em caso de dúvidas referir a licenca GNU-GPL.
  */ 

package com.contpatri.restful.inventario.jerseyspring.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "inventario")
@XmlRootElement
public class Inventario implements Serializable {

	// --------------------------------------------------------------------------
	private static final long serialVersionUID = -1058743390684974211L;
	// --------------------------------------------------------------------------

	private static final int LIMITE = 250;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(nullable = false, length = LIMITE)
	private String name;

	@Column
	private String description;

	@Column(nullable = false, length = LIMITE)
	private String url;

	// --------------------------------------------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// --------------------------------------------------------------------------

}
