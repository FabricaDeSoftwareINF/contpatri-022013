/**
 * Esse documento é parte do código fonte e artefatos relacionados 
 * ao projeto CONTPATRI, em desenvolvimento pela Fábrica de Software
 * da UFG.
 * 
 *  Links relevantes:
 *  Fábrica de Software: http://fs.inf.ufg.br/
 *  Instituto de Informática UFG: http://www.inf.ufg.br
 *  Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013
 *  Projeto CONTPATRI REDMINE: http://fs.inf.ufg.br/redmine/projects/contpatri-012013-
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

package com.contpatri.restful.inventario.jerseyspring.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.contpatri.restful.inventario.jerseyspring.persistence.dao.IInventarioDAO;
import com.contpatri.restful.inventario.jerseyspring.persistence.entity.Inventario;

@Repository("inventarioDAO")
public class InventarioDAO extends HibernateDaoSupport implements
		IInventarioDAO {

	// --------------------------------------------------------------------------

	private Logger log = LoggerFactory.getLogger(InventarioDAO.class);

	// --------------------------------------------------------------------------

	@Autowired
	public void init(SessionFactory sessionFactory) {
		log.debug("inject SessionFactory");

		super.setSessionFactory(sessionFactory);

		this.generateInitData();
	}

	// --------------------------------------------------------------------------

	public void generateInitData() {

		List<Inventario> list = this.listAll();
		final String enderecoUrl = "https://sicop.com/Inventarios_INF";

		if (list.size() == 0) {
			log.debug("generateInitData");

			// exemplos
			criaInventario("INVENTARIO INF 2013", "inventarios/inf/2013",
					enderecoUrl);

			criaInventario("INVENTARIO INF 2012", "inventarios/inf/2012",
					enderecoUrl);

			criaInventario("INVENTARIO INF 2011", "inventarios/inf/2011",
					enderecoUrl);

			criaInventario("INVENTARIO INF 2010", "inventarios/inf/2010",
					enderecoUrl);
		}
	}

	/**
	 * Cria um novo inventario
	 */
	private void criaInventario(String nome, String descricao,
			String enderecoUrl) {
		Inventario vo = new Inventario();
		vo.setName(nome);
		vo.setDescription(descricao);
		vo.setUrl(enderecoUrl);
		this.save(vo);
	}

	// --------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Inventario> listAll() {
		log.debug("listAll");
		Criteria c = super.getSession().createCriteria(Inventario.class);
		return c.list();
	}

	public Inventario findById(Long id) {
		log.debug("findById: " + id);
		return (Inventario) super.getHibernateTemplate().get(Inventario.class,
				id);
	}

	@SuppressWarnings("unchecked")
	public List<Inventario> findByName(String name) {
		log.debug("findByName: " + name);
		Criteria c = super.getSession().createCriteria(Inventario.class);
		c.add(Restrictions.like("name", "%" + name + "%"));
		return c.list();
	}

	public Inventario save(Inventario value) {
		log.debug("save");
		return super.getHibernateTemplate().merge(value);
	}

	public boolean remove(Long id) {
		log.debug("remove: " + id);
		boolean flag = true;
		try {

			super.getHibernateTemplate().delete(this.findById(id));

		} catch (DataAccessException dae) {
			flag = false;
			log.error("DataAccessException", dae);
		}
		return flag;
	}

}
