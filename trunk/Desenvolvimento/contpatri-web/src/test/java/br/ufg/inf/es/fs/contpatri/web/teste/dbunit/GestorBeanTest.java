package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.interfaces.IUsuario;
import br.ufg.inf.es.fs.contpatri.web.controller.AgenteBean;
import br.ufg.inf.es.fs.contpatri.web.controller.GestorBean;

public class GestorBeanTest {
	/**
	 * Gestor Bean
	 */
	private GestorBean gestorBean;

	/**
	 * Gestor
	 */
	private IUsuario gestor;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		gestor = new Gestor("Gestor Teste", "129785798", "testeasdasd@email.com");
		gestor.setSenha("123");
		gestorBean = new GestorBean();
	}

	@After
	/**
	 * Exclui o que foi inserido após o teste
	 */
	public void tearDown() {
		gestorBean.excluir();
	}

	@Test
	/**
	 * Teste do Agente bean no web
	 */
	public void testAgenteBean() {
		gestorBean.setUsuario(gestor);
		gestorBean.setUsuarioSelecionado(gestor);
		gestorBean.adicionar();

		gestorBean.editar();

		assertNotNull(gestorBean.getUsuarios());
		assertNotNull(gestorBean.getUsuario());
		assertEquals(gestor, gestorBean.getUsuarioSelecionado());

	}
}
