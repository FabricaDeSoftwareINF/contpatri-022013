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
package br.ufg.inf.es.fs.contpatri.persistencia.dao;

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.persistencia.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Query;

/**
 *
 * @author Guilherme de Paula
 */
public class GestorDAO extends GenericDAOImpl<Gestor, Long> implements Serializable {

    /**
     * 
     * @param email endereço de email do gestor
     * @return gestor com o email determinado
     */
    public Gestor findByEmail(String email) {
        String hql = "from Gestor where email = :email";
        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createQuery(hql).setParameter("email", email);
        Gestor gestor = findOne(query);
        HibernateUtil.commitTransaction();
        
        return gestor;
    }
}
