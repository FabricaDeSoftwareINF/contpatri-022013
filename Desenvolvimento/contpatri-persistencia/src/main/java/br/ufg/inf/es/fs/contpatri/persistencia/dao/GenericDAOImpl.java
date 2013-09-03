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

import br.ufg.inf.es.fs.contpatri.persistencia.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Implementação genérica de operações de persistência no Hibernate.
 *
 * @author Guilherme de Paula <guilherme.p.pereira at gmail.com>
 */
abstract class GenericDAOImpl<T, ID extends Serializable> implements
        GenericDAO<T, ID> {

    protected Session getSession() {
        return HibernateUtil.getSession();
    }

    @Override
    public void create(T entity) {
        try {
            HibernateUtil.beginTransaction();
            Session hibernateSession = this.getSession();
            hibernateSession.save(entity);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public void update(T entity) {
        try {
            HibernateUtil.beginTransaction();
            Session hibernateSession = this.getSession();
            hibernateSession.update(entity);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public void merge(T entity) {
        try {
            HibernateUtil.beginTransaction();
            Session hibernateSession = this.getSession();
            hibernateSession.merge(entity);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public void delete(T entity) {
        try {
            HibernateUtil.beginTransaction();
            Session hibernateSession = this.getSession();
            hibernateSession.delete(entity);
            HibernateUtil.commitTransaction();
        } catch (HibernateException e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        }
    }

    @Override
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }

    @Override
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }

    public int rowCount(Class clazz) {
        Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("select count (*) from " + clazz.getName());
        return ((Long) query.list().get(0)).intValue();
    }

    @Override
    public T findByID(Class clazz, ID id) {
        HibernateUtil.beginTransaction();
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(clazz, id);
        HibernateUtil.commitTransaction();
        return t;
    }

    @Override
    public List findAll(Class clazz) {
        HibernateUtil.beginTransaction();
        Session hibernateSession = this.getSession();
        List t = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        t = query.list();
        HibernateUtil.commitTransaction();
        return t;
    }
}
