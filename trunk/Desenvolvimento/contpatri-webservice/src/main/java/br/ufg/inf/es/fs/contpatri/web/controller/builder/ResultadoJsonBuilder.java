package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Resultado;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.util.List;
import java.util.Map;

public class ResultadoJsonBuilder extends JsonBuilder {

    public ResultadoJsonBuilder() {
        gson.registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Inventario.class, "$type").registerSubtype(Inventario.class));
        gson.registerTypeAdapterFactory(RuntimeTypeAdapterFactory.of(Resultado.class, "$type").registerSubtype(Resultado.class));
    }

    @Override
    Class getClazz() {
        return Resultado.class;
    }

    public String deObjetoParaJson(String mensagem) {
        Resultado resultado = new Resultado();
        resultado.setSucesso(true);
        resultado.setMensagem(mensagem);
        return deObjetoParaJson(resultado);
    }

    public String deObjetoParaJson(List<Inventario> inventarios, String mensagem) {
        Resultado resultado = new Resultado();
        resultado.setSucesso(true);
        resultado.setMensagem(mensagem);
        if (inventarios != null && inventarios.size() > 0)
            resultado.setDado("inventarios", inventarios);
        return deObjetoParaJson(resultado);
    }

    public String deObjetoParaJson(Exception excecao, String mensagem) {
        Resultado resultado = new Resultado();
        resultado.setSucesso(false);
        resultado.setMensagem(mensagem);
        resultado.setDado("excecao", excecao);
        return deObjetoParaJson(resultado);
    }

    public String deObjetoParaJson(Object object, Boolean sucesso, String mensagem) {
        Resultado resultado = new Resultado();
        resultado.setSucesso(sucesso);
        resultado.setMensagem(mensagem);
        if (object != null)
            resultado.setDado("object", object);
        return deObjetoParaJson(resultado);
    }

    public Resultado deJsonParaObjeto(String json) {
        Resultado resultado = (Resultado) deJsonParaObjeto(json, Resultado.class);
        for (Map.Entry<String, Object> dado : resultado.getDados().entrySet()) {
            if (dado.getValue() instanceof List) {
                List valores = (List) dado.getValue();
                for (Object item : valores) {
                    if (item instanceof Map) {
                        Map itemObjeto = (Map) item;
                        if (itemObjeto.containsKey("$type"))
                            valores.set(valores.indexOf(item), montaObjeto(item));
                    }
                }
            }
        }

        return resultado;
    }

    public Object montaObjeto(Object item) {
        try {
            return gson.create().fromJson(deObjetoParaJson(item), Class.forName((String) ((Map) item).get("$type")));
        } catch (ClassNotFoundException e) {
            return item;
        }
    }
}
