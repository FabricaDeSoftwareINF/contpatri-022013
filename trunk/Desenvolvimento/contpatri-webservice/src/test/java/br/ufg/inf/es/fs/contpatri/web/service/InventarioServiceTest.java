package br.ufg.inf.es.fs.contpatri.web.service;

import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.InventarioDAO;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * User: Halisson
 * Date: 20/09/13
 * Time: 20:56
 */
public class InventarioServiceTest extends TestCase {
    InventarioService inventarioService = new InventarioService();

    @Test
    public void testExportaDeveRetornarResultadoComTodosOsInventarios() {
        InventarioDAO inventarioDAOMock = spy(new InventarioDAOMock());
        List<Inventario> inventariosEsperados = new ArrayList<Inventario>();
        inventariosEsperados.add(new Inventario());
//        given(inventarioDAOMock.findAll(Inventario.class)).willReturn(inventariosEsperados);
        doReturn(inventariosEsperados).when(inventarioDAOMock).findAll(Inventario.class);

        inventarioService = new InventarioService(inventarioDAOMock);
        assertEquals(inventariosEsperados, inventarioService.exportar().getDado("inventarios"));
        verify(inventarioDAOMock).findAll(Inventario.class);
    }

    @Test
    public void testExportaDeveRetornarResultadoComExcecaoCasoNaoConsigaObterOsInventarios() {
        InventarioDAO inventarioDAOMock = spy(new InventarioDAOMock());
        List<Inventario> inventariosEsperados = new ArrayList<Inventario>();
        inventariosEsperados.add(new Inventario());
//        given(inventarioDAOMock.findAll(Inventario.class)).willReturn(inventariosEsperados);
        RuntimeException ex = new RuntimeException();
        doThrow(ex).when(inventarioDAOMock).findAll(Inventario.class);

        inventarioService = new InventarioService(inventarioDAOMock);
        assertEquals(ex, inventarioService.exportar().getDado("exception"));
        verify(inventarioDAOMock).findAll(Inventario.class);
    }

}

class InventarioDAOMock extends InventarioDAO {
    @Override
    public List findAll(Class clazz) {
        return new ArrayList();
    }
}