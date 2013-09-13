package br.ufg.inf.es.fs.contpatri.web.model.autenticacao;

/**
 * User: Halisson
 * Date: 12/09/13
 * Time: 23:52
 */
public class Autenticacao {
    private String login;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
