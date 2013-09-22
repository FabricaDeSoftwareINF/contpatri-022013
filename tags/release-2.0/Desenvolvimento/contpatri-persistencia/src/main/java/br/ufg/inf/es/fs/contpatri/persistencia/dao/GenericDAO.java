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

/**
 * Interface DAO genérica.
 * 
 * @author Guilherme de Paula <guilherme.p.pereira at gmail.com>
 */
import java.io.*;
import java.util.*;
import org.hibernate.Query;

interface GenericDAO<T, ID extends Serializable> {

    void create(T entity);
    
    void update(T entity);

    void merge(T entity);

    void delete(T entity);

    List<T> findMany(Query query);

    T findOne(Query query);

    List findAll(Class clazz);

    T findByID(Class clazz, ID id);
}
