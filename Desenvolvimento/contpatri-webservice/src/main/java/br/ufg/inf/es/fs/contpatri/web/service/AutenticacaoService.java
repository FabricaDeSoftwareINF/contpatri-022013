package br.ufg.inf.es.fs.contpatri.web.service;

import br.ufg.inf.es.fs.contpatri.web.model.Resultado;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Autenticacao;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia.EstrategiaDeAutenticacao;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia.EstrategiaDeAutenticacaoFake;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:30
 */
public class AutenticacaoService {
    private EstrategiaDeAutenticacao estrategiaDeAutenticacao;

    public AutenticacaoService() {
        //TODO: Substituir por EstrategiaDeAutenticacaoBD
        estrategiaDeAutenticacao = new EstrategiaDeAutenticacaoFake();
    }

    public AutenticacaoService(EstrategiaDeAutenticacao estrategiaDeAutenticacao) {
        this.estrategiaDeAutenticacao = estrategiaDeAutenticacao;
    }

    public Resultado autenticar(String login, String senha) {
        return autenticar(new Autenticacao(login, senha));
    }

    public Resultado autenticar(Autenticacao autenticacao) {
        Resultado resultado = new Resultado(true, "Login realizado com sucesso", HttpServletResponse.SC_OK);

        if (!autenticouComSucesso(autenticacao.getLogin(), autenticacao.getSenha())) {
            resultado.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resultado.setSucesso(false);
            resultado.setMensagem("Erro ao tentar realizar o login");
        }

        return resultado;
    }

    private boolean autenticouComSucesso(String login, String senha) {
        return estrategiaDeAutenticacao.autenticouComSucesso(login, senha);
    }
}
