/*
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
package br.ufg.inf.es.fs.contpatri.persistencia.dao;

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.persistencia.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Guilherme de Paula
 */
public class InventarioDAO extends GenericDAOImpl<Inventario, Long>  implements Serializable{
    
    /**
     *
     * @return último inventario emitido
     */
    public Inventario findUltimoInventarioEmitido(){
        String hql = "from Inventario order by dataEmissao desc";
        Session session = HibernateUtil.beginTransaction();
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        Inventario inventario = findOne(query);
        HibernateUtil.commitTransaction();
        
        return inventario;
    }
    
}
