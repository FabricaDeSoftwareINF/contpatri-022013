/**
  * Esse documento e parte do codigo fonte e artefatos relacionados 
  * ao projeto CONTPATRI, em desenvolvimento pela Fabrica de Software
  * da UFG.
  * 
  *  Links relevantes:
  *  Fabrica de Software: http://fs.inf.ufg.br/
  *  Instituto de Informatica UFG: http://www.inf.ufg.br
  *  Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013
  *  Projeto CONTPATRI REDMINE: http://fs.inf.ufg.br/redmine/projects/contpatri-012013-
  *
  * Copyleft � UFG.
  * 
  * Licenciado sobre a licença GNU-GPL v3
  *
  * Voce pode obter uma c�pia da licen�a em http://www.gnu.org/licenses/gpl.html
  * 
  * A menos que especificado ou exigido por legisla��o local, o software � 
  * fornecido "da maneira que está", sem garantias ou condições de qualquer 
  * tipo, nem expressas nem implicitas. Em caso de d�vidas referir a licença GNU-GPL.
  */ 

package com.contpatri.restful.inventario.jerseyspring.persistence.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.contpatri.restful.inventario.jerseyspring.persistence.entity.Inventario;

@Transactional
public interface IInventarioDAO {

	List<Inventario> listAll();

	Inventario findById(Long id);

	List<Inventario> findByName(String name);

	Inventario save(Inventario value);

	boolean remove(Long id);

}
