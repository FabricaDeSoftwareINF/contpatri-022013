package br.ufg.inf.es.fs.contpatri.web.service;

import br.ufg.inf.es.fs.contpatri.web.model.Resultado;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Autenticacao;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:30
 */
public class AutenticacaoService {
    public Resultado autenticar(Autenticacao autenticacao) {
        Resultado resultado = new Resultado(true, "Login realizado com sucesso", HttpServletResponse.SC_OK);

        if (!autenticouComSucesso(autenticacao.getLogin(), autenticacao.getSenha())) {
            resultado.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resultado.setMensagem("Erro ao tentar realizar o login");
        }

        return resultado;
    }

    private boolean autenticouComSucesso(String login, String senha) {
        //TODO: Implementar autenticação real
        return (login.equals("admin@inf.ufg.br") && (senha.equals("S&CR&7")));
    }
}
