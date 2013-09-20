package br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:51
 */
public class EstrategiaDeAutenticacaoFake implements EstrategiaDeAutenticacao {
    @Override
    public boolean autenticouComSucesso(String login, String senha) {
        return (login.equals("admin@inf.ufg.br") && (senha.equals("S&CR&7")));
    }
}
