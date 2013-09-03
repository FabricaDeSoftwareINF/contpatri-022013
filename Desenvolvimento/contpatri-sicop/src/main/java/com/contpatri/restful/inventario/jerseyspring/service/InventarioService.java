/**
  * Esse documento � parte do c�digo fonte e artefatos relacionados 
  * ao projeto CONTPATRI, em desenvolvimento pela F�brica de Software
  * da UFG.
  * 
  *  Links relevantes:
  *  F�brica de Software: http://fs.inf.ufg.br/
  *  Instituto de Inform�tica UFG: http://www.inf.ufg.br
  *  Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013
  *  Projeto CONTPATRI REDMINE: http://fs.inf.ufg.br/redmine/projects/contpatri-012013-
  *
  * Copyleft � UFG.
  * 
  * Licenciado sobre a licen�a GNU-GPL v3
  *
  * Voc� pode obter uma c�pia da licen�a em http://www.gnu.org/licenses/gpl.html
  * 
  * A menos que especificado ou exigido por legisla��o local, o software � 
  * fornecido "da maneira que est�", sem garantias ou condi��es de qualquer 
  * tipo, nem expressas nem impl�citas. Em caso de d�vidas referir a licen�a GNU-GPL.
  */ 

package com.contpatri.restful.inventario.jerseyspring.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.contpatri.restful.inventario.jerseyspring.persistence.dao.IInventarioDAO;
import com.contpatri.restful.inventario.jerseyspring.persistence.entity.Inventario;

@Component
@Scope("prototype")
@Path("/inventario")
public class InventarioService {

	// --------------------------------------------------------------------------
	
	private Logger log = LoggerFactory.getLogger(InventarioService.class);
	
	// --------------------------------------------------------------------------

	@Autowired
	private IInventarioDAO dao;

	// --------------------------------------------------------------------------

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Inventario> getAll() {
		log.debug("getAll");
		return dao.listAll();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Inventario getById(@PathParam("id") String id) {
		log.debug("getById: " + id);
		return dao.findById(Long.valueOf(id));
	}

	@GET
	@Path("search/{name}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Inventario> getByName(@PathParam("name") String name) {
		log.debug("getByName: " + name);
		return dao.findByName(name);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Inventario insert(Inventario value) {
		log.debug("insert");
		return dao.save(value);
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Inventario update(Inventario value) {
		log.debug("update");
		return dao.save(value);
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(@PathParam("id") String id) {
		boolean flag = dao.remove(Long.valueOf(id));
		log.debug("remove: " + id + " | status: " + flag);
	}

}
