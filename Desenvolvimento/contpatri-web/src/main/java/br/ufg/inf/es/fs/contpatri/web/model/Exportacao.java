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

import br.ufg.inf.es.fs.contpatri.model.Analise;
import br.ufg.inf.es.fs.contpatri.model.Coleta;
import com.google.gson.annotations.Expose;
import java.util.Set;

/**
 *
 * @author Guilherme de Paula
 */
public class Exportacao {
    
    @Expose
    private Long idInventario;
    @Expose
    private Set<Analise> analises;
    @Expose
    private Set<Coleta> coletas;

    public Exportacao() {
    }
    
    public Exportacao(Long idInventario, Set<Analise> analises, Set<Coleta> coletas) {
        this.idInventario = idInventario;
        this.analises = analises;
        this.coletas = coletas;
    }

    
    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }
    
    public Set<Analise> getAnalises() {
        return analises;
    }

    public void setAnalises(Set<Analise> analises) {
        this.analises = analises;
    }

    public Set<Coleta> getColetas() {
        return coletas;
    }

    public void setColetas(Set<Coleta> coletas) {
        this.coletas = coletas;
    }
}
