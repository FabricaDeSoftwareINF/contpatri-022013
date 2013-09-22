package br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:50
 */
public interface EstrategiaDeAutenticacao {
    public boolean autenticouComSucesso(String login, String senha);
}
