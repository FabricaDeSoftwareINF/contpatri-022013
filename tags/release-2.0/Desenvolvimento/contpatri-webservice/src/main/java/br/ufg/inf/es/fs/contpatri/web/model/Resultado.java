package br.ufg.inf.es.fs.contpatri.web.model;

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
    private Integer status;
    private Map<String, Object> dados = new HashMap<String, Object>();

    public Resultado() {
    }

    public Resultado(Boolean sucesso, String mensagem, Integer status) {
        this(sucesso, mensagem, status, null);
    }

    public Resultado(Boolean sucesso, String mensagem, Integer status, Map<String, Object> dados) {
        setSucesso(sucesso);
        setMensagem(mensagem);
        setStatus(status);
        if (dados != null) {
            for (Map.Entry<String, Object> dado : dados.entrySet()) {
                setDado(dado.getKey(), dado.getValue());
            }
        }
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isSucesso() {
        return getSucesso();
    }

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
