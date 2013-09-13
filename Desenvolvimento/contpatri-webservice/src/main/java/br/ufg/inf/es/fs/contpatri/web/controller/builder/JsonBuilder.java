package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

abstract public class JsonBuilder<T> {
    protected GsonBuilder gson;

    public JsonBuilder() {
        gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
        ;
    }

    abstract Class<T> getClazz();

    public T deJsonParaObjeto(String json) {
        return (T) deJsonParaObjeto(json, getClazz());
    }

    protected String deObjetoParaJson(Object object) {
        return gson.create().toJson(object);
    }

    protected Object deJsonParaObjeto(String json, Class clazz) {
        JsonParser parser = new JsonParser();
        parser.parse(json);

        return gson.create().fromJson(json, clazz);
    }
}
