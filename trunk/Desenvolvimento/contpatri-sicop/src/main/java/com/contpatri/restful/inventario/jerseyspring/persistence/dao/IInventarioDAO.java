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
  * Copyleft ï¿½ UFG.
  * 
  * Licenciado sobre a licenÃ§a GNU-GPL v3
  *
  * Voce pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html
  * 
  * A menos que especificado ou exigido por legislação local, o software ï¿½ 
  * fornecido "da maneira que estÃ¡", sem garantias ou condiÃ§Ãµes de qualquer 
  * tipo, nem expressas nem implicitas. Em caso de dï¿½vidas referir a licenÃ§a GNU-GPL.
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
