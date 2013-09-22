/**
 * Esse documento e parte do codigo fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fabrica de Software da UFG.
 *
 * Links relevantes: Fabrica de Software: http://fs.inf.ufg.br/ Instituto de
 * Informatica UFG: http://www.inf.ufg.br Projeto CONTPATRI DROPBOX:
 * https://www.dropbox.com/home/CONTPATRI%20-%20012013 Projeto CONTPATRI
 * REDMINE:
 *
 * Copyleft c UFG.
 *
 * Licenciado sobre a licenca GNU-GPL v3
 *
 * Voce pode obter uma copia da licenca em http://www.gnu.org/licenses/gpl.html
 *
 * A menos que especificado ou exigido por legislacao local, o software e
 * fornecido "da maneira que esta", sem garantias ou condicoes de qualquer tipo,
 * nem expressas nem implicitas. Em caso de dúvidas referir a licenca GNU-GPL.
 */
package br.ufg.inf.es.fs.contpatri.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Guilherme de Paula <guilherme.p.pereira at gmail.com>
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            SESSION_FACTORY = new AnnotationConfiguration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Inicia a transação
     *
     * @return sessão com a transação iniciada
     */
    public static Session beginTransaction() {
        Session hibernateSession = HibernateUtil.getSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    /**
     * Comita a transação da sessão
     */
    public static void commitTransaction() {
        HibernateUtil.getSession().getTransaction().commit();
    }

    /**
     * Da um "rollback" na transação
     */
    public static void rollbackTransaction() {
        HibernateUtil.getSession().getTransaction().rollback();
    }

    /**
     * Fecha a sessão
     */
    public static void closeSession() {
        HibernateUtil.getSession().close();
    }

    /**
     *
     * @return sessão atual
     */
    public static Session getSession() {
        return SESSION_FACTORY.getCurrentSession();
    }
}
