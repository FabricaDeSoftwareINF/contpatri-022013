package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.GestorDAO;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.InventarioDAO;
import br.ufg.inf.es.fs.contpatri.web.controller.ExportarBean;

public class ExportarBeanTest {
	/**
	 * Exportar Bean
	 */
	private ExportarBean exportarBean;

	/**
	 * Inventário
	 */
	private Inventario inventario;

	private Gestor gestor;

	/**
	 * Dao Inventário
	 */
	private InventarioDAO dao;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		exportarBean = new ExportarBean();
		dao = new InventarioDAO();
		List<BemPatrimonial> list = new ArrayList<BemPatrimonial>();
		list.add(new BemPatrimonial());
		gestor = new Gestor("Gestor Teste", "1123323", "gestorasdasd@email.tst");
		gestor.setSenha("123151512341");

		GestorDAO daog = new GestorDAO();
		daog.create(gestor);

		inventario = new Inventario(new Date(), daog.findByID(Gestor.class,
				gestor.getId()), list);

		dao.create(inventario);
	}

	@After
	/**
	 * Tear down dos testes
	 */
	public void tearDown() {
		dao.delete(dao.findByID(Inventario.class, inventario.getId()));
		GestorDAO daog = new GestorDAO();
		daog.delete(daog.findByID(Gestor.class, gestor.getId()));
	}

	@Test
	/**
	 * Teste do método isExportacaoIniciada
	 */
	public void testIniciada() {
		exportarBean.setExportacaoIniciada(false);

		assertEquals(false, exportarBean.isExportacaoIniciada());
	}

	@Test
	/**
	 * Teste do método realizarExportacao
	 */
	public void testRealizarExportacao() {
		try {
			exportarBean.realizarExportacao();
		} catch (Exception e) {

		}

		assertNotNull(exportarBean.getArquivo());
	}
}
