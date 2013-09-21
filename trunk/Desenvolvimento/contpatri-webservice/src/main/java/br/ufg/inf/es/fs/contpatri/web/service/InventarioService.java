package br.ufg.inf.es.fs.contpatri.web.service;

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.InventarioDAO;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.TombamentoDAO;
import br.ufg.inf.es.fs.contpatri.web.model.Resultado;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:15
 */
public class InventarioService {
    private TombamentoDAO tombamentoDAO;
    private InventarioDAO inventarioDAO;

    public InventarioService() {
        this(new InventarioDAO(), new TombamentoDAO());
    }

    public InventarioService(InventarioDAO inventarioDAO) {
        this(inventarioDAO, new TombamentoDAO());
    }

    public InventarioService(TombamentoDAO tombamentoDAO) {
        this(new InventarioDAO(), tombamentoDAO);
    }

    public InventarioService(InventarioDAO inventarioDAO, TombamentoDAO tombamentoDAO) {
        this.inventarioDAO = inventarioDAO;
        this.tombamentoDAO = tombamentoDAO;
    }

    public Resultado exportar() {
        Resultado resultado = new Resultado(
                true,
                "Lista de inventários gerada com sucesso",
                HttpServletResponse.SC_OK
        );

        try {
            List<Inventario> inventarios = inventarioDAO.findAll(Inventario.class);
            resultado.setDado("inventarios", inventarios);
        } catch (Exception ex) {
            resultado.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resultado.setMensagem("Houve um erro ao tentar exportar os inventários");
            resultado.setDado("exception", ex);
        }

        return resultado;
    }

    public Resultado importarColetas(List<Tombamento> tombamentos) {
        Resultado resultado = new Resultado(true, "Coletas cadastradas com sucesso", HttpServletResponse.SC_OK);

        try {
            for (Tombamento tombamento : tombamentos) {
                tombamentoDAO.create(tombamento);
            }

            resultado.setDado("tombamentos", tombamentos);
        } catch (Exception ex) {
            resultado.setSucesso(false);
            resultado.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resultado.setMensagem("Houve um erro ao tentar exportar os inventários");
            resultado.setDado("exception", ex);
        }

        return resultado;
    }
}
