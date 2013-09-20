package br.ufg.inf.es.fs.contpatri.web.model.autenticacao;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Halisson
 * Date: 12/09/13
 * Time: 00:01
 */
public class Resultado {
    private Boolean sucesso;
    private String mensagem;
    private Map<String, Object> dados = new HashMap<String, Object>();

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object setDado(String chave, Object valor) {
        return dados.put(chave, valor);
    }

    public Object getDado(String chave) {
        return dados.get(chave);
    }

    public Object removeDado(String chave) {
        return dados.remove(chave);
    }

    public Map<String, Object> getDados() {
        return dados;
    }
}
