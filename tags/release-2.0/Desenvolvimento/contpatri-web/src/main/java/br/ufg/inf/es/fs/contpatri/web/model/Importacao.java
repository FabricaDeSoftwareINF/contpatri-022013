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
package br.ufg.inf.es.fs.contpatri.web.model;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import com.google.gson.annotations.Expose;
import java.util.Set;

/**
 *
 * @author Guilherme de Paula
 */
public class Importacao {
    
    @Expose
    private Long idInventario;
    @Expose
    private Set<BemPatrimonial> bensPatrimoniais;

    public Importacao() {
    }

    public Importacao(Long idInventario, Set<BemPatrimonial> bensPatrimoniais) {
        this.idInventario = idInventario;
        this.bensPatrimoniais = bensPatrimoniais;
    }
    
    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }
    
    public Set<BemPatrimonial> getBensPatrimoniais() {
        return bensPatrimoniais;
    }

    public void setBensPatrimoniais(Set<BemPatrimonial> bensPatrimoniais) {
        this.bensPatrimoniais = bensPatrimoniais;
    }
}
