package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.InventarioDAO;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.TombamentoDAO;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.ResultadoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.TombamentoJsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * User: Halisson
 * Date: 11/09/13
 * Time: 23:18
 */
@Controller
@RequestMapping("/inventario")
public class InventarioController {
    private ResultadoJsonBuilder jsonBuilder = new ResultadoJsonBuilder();

    @RequestMapping(value = "/exportar", method = RequestMethod.GET)
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String retorno = "";
        try {
            List<Inventario> inventarios = new InventarioDAO().findAll(Inventario.class);
            retorno = jsonBuilder.deObjetoParaJson(inventarios, "Lista de inventários gerada com sucesso");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            retorno = jsonBuilder.deObjetoParaJson(ex, "Houve um erro ao tentar exportar os inventários");
        } finally {
            System.out.println(retorno);
            response.getWriter().write(retorno);
            response.getWriter().close();
        }
    }

    @RequestMapping(value = "/coletas", method = RequestMethod.POST)
    public void importarColetas(@RequestBody String json, HttpServletResponse response) throws IOException {
        List<Tombamento> tombamentos = new TombamentoJsonBuilder().deJsonParaListaDeObjetos(json);
        System.out.println(tombamentos);
        String retorno = "";

        try {
            TombamentoDAO tombamentoDAO = new TombamentoDAO();
            for (Tombamento tombamento : tombamentos) {
                tombamentoDAO.create(tombamento);
            }
            retorno = jsonBuilder.deObjetoParaJson(tombamentos, true, "Coleta cadastrada com sucesso");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception ex) {
            retorno = jsonBuilder.deObjetoParaJson(ex, "Houve um erro ao tentar exportar os inventários");
        } finally {
            System.out.println(retorno);
            response.getWriter().write(retorno);
            response.getWriter().close();
        }
    }
}
