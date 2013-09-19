package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.web.controller.builder.AutenticacaoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.model.Resultado;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Autenticacao;
import br.ufg.inf.es.fs.contpatri.web.service.AutenticacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Halisson
 * Date: 11/09/13
 * Time: 23:18
 */
@Controller
@RequestMapping("/autenticacao")
public class AutenticacaoController extends AbstractController {
    private AutenticacaoService autenticacaoService = new AutenticacaoService();

    @RequestMapping(method = RequestMethod.POST)
    public void autenticar(@RequestBody String json, HttpServletResponse response) throws IOException {
        Autenticacao autenticacao = new AutenticacaoJsonBuilder().deJsonParaObjeto(json);
        Resultado resultado = autenticacaoService.autenticar(autenticacao);
        renderizeResultado(resultado, response);
    }
}
