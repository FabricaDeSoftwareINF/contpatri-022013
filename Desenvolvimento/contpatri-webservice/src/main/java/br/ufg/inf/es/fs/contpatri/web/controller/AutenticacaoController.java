package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.web.controller.builder.AutenticacaoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.ResultadoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Autenticacao;
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
public class AutenticacaoController {

    @RequestMapping(method = RequestMethod.POST)
    public void autenticar(@RequestBody String json, HttpServletResponse response) throws IOException {
        Autenticacao autenticacao = new AutenticacaoJsonBuilder().deJsonParaObjeto(json);
        String retorno = "";

        if (autenticacao.getLogin().equals("admin@inf.ufg.br") && (autenticacao.getSenha().equals("S&CR&7"))) {
            retorno = new ResultadoJsonBuilder().deObjetoParaJson("Login realizado com sucesso");
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            retorno = new ResultadoJsonBuilder().deObjetoParaJson(null, false, "Erro ao tentar realizar o login");
        }

        response.getWriter().write(retorno);
        response.getWriter().close();
    }
}
