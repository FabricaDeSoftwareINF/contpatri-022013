package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.ResultadoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.TombamentoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.model.Resultado;
import br.ufg.inf.es.fs.contpatri.web.service.InventarioService;
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
public class InventarioController extends AbstractController {
    private ResultadoJsonBuilder jsonBuilder = new ResultadoJsonBuilder();
    private InventarioService inventarioService = new InventarioService();

    /**
     * Action responsável pela exportação da lista de inventários do sistema.
     * A Action espera um GET e retornará:
     * <p/>
     * {
     * "$type": "br.ufg.inf.es.fs.contpatri.web.model.Resultado",
     * "sucesso": true,
     * "mensagem": "Lista de invent�rios gerada com sucesso",
     * "status": null,
     * "dados": {}
     * }
     */
    @RequestMapping(value = "/exportar", method = RequestMethod.GET)
    public void exportar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Resultado resultado = inventarioService.exportar(response);
        renderizeResultado(resultado, response);
    }

    /**
     * Action responsável pela importação de uma coleta de tombamentos.
     * A Action espera o POST de um json no formato:
     * <p/>
     * [
     * {
     * "codigo":338178,
     * "observacao":"Observacao Teste",
     * "situacao":"Localizado",
     * "sublocal":"Sublocal 1",
     * "ultimaAlteracao":1379030902416
     * },
     * {
     * "codigo":338129,
     * "observacao":"Observacao Teste",
     * "situacao":"Localizado",
     * "sublocal":"Sublocal 1",
     * "ultimaAlteracao":1379030902416
     * },
     * {
     * "codigo":338149,
     * "observacao":"Observacao Teste",
     * "situacao":"Localizado",
     * "sublocal":"Sublocal 1",
     * "ultimaAlteracao":1379030902416
     * }
     * ]
     * <p/>
     * <p/>
     * Ele retornará uma resposta no formato:
     * <p/>
     * <p/>
     * {
     * "$type": "br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Resultado",
     * "sucesso": true,
     * "mensagem": "Coleta cadastrada com sucesso",
     * "dados": {
     * "object": [
     * {
     * "id": 10,
     * "codTombamento": "338178",
     * "dataTombamento": "Sep 12, 2013 9:08:22 PM",
     * "tipoTombamento": "INTERNO"
     * },
     * {
     * "id": 11,
     * "codTombamento": "338129",
     * "dataTombamento": "Sep 12, 2013 9:08:22 PM",
     * "tipoTombamento": "INTERNO"
     * },
     * {
     * "id": 12,
     * "codTombamento": "338149",
     * "dataTombamento": "Sep 12, 2013 9:08:22 PM",
     * "tipoTombamento": "INTERNO"
     * }
     * ]
     * }
     * }
     */
    @RequestMapping(value = "/coletas", method = RequestMethod.POST)
    public void importarColetas(@RequestBody String json, HttpServletResponse response) throws IOException {
        List<Tombamento> tombamentos = new TombamentoJsonBuilder().deJsonParaListaDeObjetos(json);
        Resultado resultado = inventarioService.importarColetas(tombamentos);
        renderizeResultado(resultado, response);
    }
}
