package br.ufg.inf.es.fs.contpatri.web.controller.inventario;

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.web.controller.builder.ResultadoJsonBuilder;
import br.ufg.inf.es.fs.contpatri.web.model.Resultado;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ResultadoJsonBuilderTest extends TestCase {
    public void testAdicionaInventariosNoResultadoJson() throws CloneNotSupportedException {
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        inventario.setGestor(new Gestor("Maluco", "123", "g@a.c"));
        List<Inventario> inventarios = new ArrayList<Inventario>();
        inventarios.add(inventario);

        ResultadoJsonBuilder jsonBuilder = new ResultadoJsonBuilder();
        String json = jsonBuilder.deObjetoParaJson(inventarios, "Lista de inventarios gerada com sucesso");
        Resultado resultado = jsonBuilder.deJsonParaObjeto(json);

        Inventario inventarioConvertido = (Inventario) ((List) resultado.getDado("inventarios")).get(0);

        assertEquals(inventario.getId(), inventarioConvertido.getId());
        assertEquals("Maluco", inventarioConvertido.getGestor().getNome());
    }
}
