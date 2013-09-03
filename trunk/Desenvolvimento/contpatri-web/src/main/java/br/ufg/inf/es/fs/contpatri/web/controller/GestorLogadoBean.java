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
package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.GestorDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Guilherme de Paula
 */
@ManagedBean
@SessionScoped
public class GestorLogadoBean {

    private GestorDAO gestorDAO;
    private Gestor gestor;
    
    /**
     * Creates a new instance of GestorLogadoBean
     */
    public GestorLogadoBean() {
        gestorDAO = new GestorDAO();
        
        // Gestor atualmente logado
        gestor = gestorDAO.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     *
     * @return gestor atualmente logado
     */
    public Gestor getGestor() {
        return gestor;
    }

    /**
     *
     * @param gestor
     */
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    
    
}
