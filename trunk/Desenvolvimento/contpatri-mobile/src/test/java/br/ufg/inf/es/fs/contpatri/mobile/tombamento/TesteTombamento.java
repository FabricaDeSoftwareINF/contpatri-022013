package br.ufg.inf.es.fs.contpatri.mobile.tombamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TesteTombamento {

	private Tombamento tombamento;

	@Before
	public void setUp() throws Exception {
		tombamento = new Tombamento();
		tombamento.setUltimaAlteracao();
		tombamento.setCodigo(12345);
		tombamento.setObservacao("Observação Teste");
		tombamento.setSituacao("Relocado");
		tombamento.setSublocal("SubLocal Teste");
	}

	@After
	public void tearDown() throws Exception {
		tombamento = null;
	}

	@Test
	public void testEqualsObject() {

		final Tombamento tombamento_teste_1 = new Tombamento();
		final Tombamento tombamento_teste_2 = null;
		final Tombamento tombamento_teste_3 = new Tombamento();
		tombamento_teste_3.setCodigo(1234);

		assertFalse("Esperado false", tombamento.equals(tombamento_teste_1));
		assertTrue("Esperado true", tombamento.equals(tombamento));

		assertFalse("Esperado false", tombamento.equals(null));
		assertFalse("Esperado false", tombamento.equals(tombamento_teste_2));

		assertFalse("Esperado false", tombamento.equals(new String()));
		assertFalse("Esperado false", tombamento.equals(tombamento_teste_3));

	}

	@Test
	public void testTombamentoConstructor() {
		final Tombamento tombamento_teste_1 = new Tombamento(123, "Localizado",
				"SubLocalTeste", "ObservaçãoTeste");
		assertEquals("Esperado 123", tombamento_teste_1.getCodigo(), 123);
		assertEquals("Localizado", tombamento_teste_1.getSituacao(),
				"Localizado");
		assertEquals("SubLocalTeste", tombamento_teste_1.getSublocal(),
				"SubLocalTeste");
		assertEquals("ObservaçãoTeste", tombamento_teste_1.getObservacao(),
				"ObservaçãoTeste");
	}

}
