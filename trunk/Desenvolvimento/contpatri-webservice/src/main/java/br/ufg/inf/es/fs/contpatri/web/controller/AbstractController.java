package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.web.controller.builder.ResultadoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.model.Resultado;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:23
 */
public abstract class AbstractController {
    protected ResultadoJsonBuilder jsonBuilder = new ResultadoJsonBuilder();

    protected void renderizeResultado(Resultado resultado, HttpServletResponse response) throws IOException {
        response.setStatus(resultado.getStatus());
        System.out.println(resultado.getDados());
        response.getWriter().write(jsonBuilder.deObjetoParaJson(resultado));
        response.getWriter().close();
    }
}
