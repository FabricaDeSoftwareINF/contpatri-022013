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

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Guilherme de Paula
 */
@ManagedBean
@ViewScoped
public class ImportarBean implements Serializable{

    private UploadedFile arquivo;
    private String mensagem;
    
    /**
     * Creates a new instance of ImportarBean
     */
    public ImportarBean() {
    }

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public void upload(){
        ImportacaoController importacaoController = new ImportacaoController();
        try {
            importacaoController.importarJson(arquivo.getInputstream());
            mensagem = "Arquivo importado com sucesso";
        } catch (Exception ex) {
            mensagem = "Ocorreu um erro na importação do arquivo: " + ex.getMessage();
        }
    }
}
