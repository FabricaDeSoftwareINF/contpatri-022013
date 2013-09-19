package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import br.ufg.inf.es.fs.contpatri.model.TipoTombamento;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

    public List<Tombamento> deJsonParaListaDeObjetos(String json) {
        JsonParser parser = new JsonParser();
        JsonElement jsonL = parser.parse(json);
        JsonArray array = jsonL.getAsJsonArray();
        List<Tombamento> tombamentos = new ArrayList<Tombamento>();
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JsonElement jsonElement = (JsonElement)iterator.next();
            String codTombamento = jsonElement.getAsJsonObject().get("codigo").getAsString();
            Date dataTombamento = new Date(jsonElement.getAsJsonObject().get("ultimaAlteracao").getAsLong());
            tombamentos.add(new Tombamento(codTombamento, dataTombamento, TipoTombamento.INTERNO));
        }

        return tombamentos;
    }
}
