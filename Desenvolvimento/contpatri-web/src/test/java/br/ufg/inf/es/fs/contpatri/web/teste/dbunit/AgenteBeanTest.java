package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Agente;
import br.ufg.inf.es.fs.contpatri.model.interfaces.IUsuario;
import br.ufg.inf.es.fs.contpatri.web.controller.AgenteBean;

public class AgenteBeanTest {
	/**
	 * Agente
	 */
	private IUsuario agente;
	/**
	 * Agente Bean
	 */
	private AgenteBean ab;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		agente = new Agente("Teste", "129785798", "teste@email.com");
		agente.setSenha("123");
		ab = new AgenteBean();
	}

	@After
	/**
	 * Exclui o que foi inserido após o teste
	 */
	public void tearDown() {
		ab.excluir();
	}

	@Test
	/**
	 * Teste do Agente bean no web
	 */
	public void testAgenteBean() {
		ab.setUsuario(agente);
		ab.setUsuarioSelecionado(agente);
		ab.adicionar();

		ab.editar();

		assertNotNull(ab.getUsuarios());
		assertNotNull(ab.getUsuario());
		assertEquals(agente, ab.getUsuarioSelecionado());

	}

}
