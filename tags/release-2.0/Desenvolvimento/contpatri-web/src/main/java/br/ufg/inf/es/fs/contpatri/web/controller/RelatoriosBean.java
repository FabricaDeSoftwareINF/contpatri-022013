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
package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.TipoSituacao;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.BemPatrimonialDAO;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Guilherme de Paula
 */
@ManagedBean
@ViewScoped
public class RelatoriosBean implements Serializable {

    private LazyDataModel<BemPatrimonial> bemPatrimonialDataModel;
    private BemPatrimonial bemPatrimonialSelecionado;
    private BemPatrimonialDAO bemPatrimonialDAO;

    /**
     * Creates a new instance of RelatoriosBean
     */
    public RelatoriosBean() {
        bemPatrimonialDataModel = new BemPatrimonialDataModel();
        bemPatrimonialDAO = new BemPatrimonialDAO();
    }

    /**
     *
     * @param event evento de edição da tabela (chamado pelo primefaces)
     */
    public void onEdit(RowEditEvent event) {
        BemPatrimonial bemPatrimonial = (BemPatrimonial) event.getObject();

        FacesMessage msg;
        try {
            bemPatrimonialDAO.update(bemPatrimonial);
            msg = new FacesMessage("Registro alterado com sucesso");
            msg.setDetail("");
        } catch (HibernateException e) {
            msg = new FacesMessage("Ocorreu um erro na alteração do registro");
            msg.setDetail(e.getMessage());
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     *
     * @return 
     */
    public LazyDataModel getBemPatrimonialDataModel() {
        return bemPatrimonialDataModel;
    }

    /**
     *
     * @param bemPatrimonialDataModel
     */
    public void setBemPatrimonialDataModel(LazyDataModel bemPatrimonialDataModel) {
        this.bemPatrimonialDataModel = bemPatrimonialDataModel;
    }

    /**
     *
     * @return registro selecionado na tabela
     */
    public BemPatrimonial getBemPatrimonialSelecionado() {
        return bemPatrimonialSelecionado;
    }

    /**
     *
     * @param bemPatrimonialSelecionado registro selecionado na tabela
     */
    public void setBemPatrimonialSelecionado(BemPatrimonial bemPatrimonialSelecionado) {
        this.bemPatrimonialSelecionado = bemPatrimonialSelecionado;
    }

    /**
     *
     * @return tipos de situação possíveis
     */
    public List<TipoSituacao> getTiposAnalise() {
        return Arrays.asList(TipoSituacao.values());
    }
}
