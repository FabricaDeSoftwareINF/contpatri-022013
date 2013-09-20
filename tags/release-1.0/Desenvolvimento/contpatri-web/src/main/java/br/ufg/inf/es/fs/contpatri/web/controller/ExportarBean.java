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

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.persistencia.JsonUtil;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.InventarioDAO;
import br.ufg.inf.es.fs.contpatri.web.model.Exportacao;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Guilherme de Paula
 */
@ManagedBean
@ViewScoped
public class ExportarBean implements Serializable {

    private StreamedContent arquivo;
    private InventarioDAO inventarioDAO;
    private boolean exportacaoIniciada;

    /**
     * Creates a new instance of ExportarBean
     */
    public ExportarBean() {
    }

    /**
     *
     */
    public void realizarExportacao() {
        exportacaoIniciada = true;
        InputStream inputStream = null;
        JsonUtil jsonUtil = new JsonUtil();
        inventarioDAO = new InventarioDAO();
        Inventario inventario = inventarioDAO.findUltimoInventarioEmitido();
        Exportacao exportacao = new Exportacao(inventario.getId(),
                inventario.getAnalises(), inventario.getColetas());
        try {
            inputStream = jsonUtil.deJsonParaInputStream(exportacao);
        } catch (IOException ex) {
            Logger.getLogger(ExportarBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        arquivo = new DefaultStreamedContent(inputStream, "application/json", "inventario.json");

    }

    /**
     *
     * @return
     */
    public StreamedContent getArquivo() {
        return arquivo;
    }

    /**
     *
     * @return
     */
    public boolean isExportacaoIniciada() {
        return exportacaoIniciada;
    }

    /**
     *
     * @param exportacaoIniciada
     */
    public void setExportacaoIniciada(boolean exportacaoIniciada) {
        this.exportacaoIniciada = exportacaoIniciada;
    }
}
