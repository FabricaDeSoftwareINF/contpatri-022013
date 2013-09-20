package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import br.ufg.inf.es.fs.contpatri.model.TipoTombamento;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Date;

/**
 * User: Halisson
 * Date: 13/09/13
 * Time: 00:42
 */
public class TombamentoJsonBuilder extends JsonBuilder<Tombamento> {
    @Override
    Class<Tombamento> getClazz() {
        return Tombamento.class;
    }

    @Override
    public Tombamento deJsonParaObjeto(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(json);
        String codTombamento = jsonElement.getAsJsonObject().get("codigo").getAsString();
        Date dataTombamento = new Date(jsonElement.getAsJsonObject().get("ultimaAlteracao").getAsLong());
        return new Tombamento(codTombamento, dataTombamento, TipoTombamento.INTERNO);
    }
}
