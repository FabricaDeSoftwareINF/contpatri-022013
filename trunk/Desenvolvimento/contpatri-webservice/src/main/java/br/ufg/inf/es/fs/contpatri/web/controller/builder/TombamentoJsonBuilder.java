package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.model.TipoTombamento;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
            JsonObject jsonElement = ((JsonElement) iterator.next()).getAsJsonObject();
            String codTombamento = jsonElement.get("codigo").getAsString();
            Date dataTombamento = new Date(jsonElement.get("ultimaAlteracao").getAsLong());
            Tombamento tombamento = new Tombamento(codTombamento, dataTombamento, TipoTombamento.INTERNO);

            String nomeSubLocal = jsonElement.get("sublocal").getAsString();
            if (nomeSubLocal != null) {
                SubLocal subLocal = new SubLocal(nomeSubLocal);
                System.out.println("Sublocal: " + nomeSubLocal);
                tombamento.setSubLocal(subLocal);
            }

            tombamentos.add(tombamento);
        }

        return tombamentos;
    }
}
