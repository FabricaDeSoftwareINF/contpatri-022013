package br.ufg.inf.es.fs.contpatri.mobile.tombamento;

import junit.framework.TestCase;

public class TesteTombamento extends TestCase {

	private Tombamento tombamento;

	@Override
	public void setUp() {
		tombamento = new Tombamento();
		tombamento.setUltimaAlteracao();
		tombamento.setCodigo(12345);
		tombamento.setObservacao("Observação Teste");
		tombamento.setSituacao("Relocado");
		tombamento.setSublocal("SubLocal Teste");
	}

	public void testEqualsObject() {

		final Tombamento tombamento_teste_1 = new Tombamento();
		final Tombamento tombamento_teste_2 = null;
		final Tombamento tombamento_teste_3 = new Tombamento();
		tombamento_teste_3.setCodigo(1234);

		assertFalse("Teste equals - 1/6", tombamento.equals(tombamento_teste_1));
		assertTrue("Teste equals - 2/6", tombamento.equals(tombamento));

		assertFalse("Teste equals - 3/6", tombamento.equals(null));
		assertFalse("Teste equals - 4/6", tombamento.equals(tombamento_teste_2));

		assertFalse("Teste equals - 5/6", tombamento.equals(new String()));
		assertFalse("Teste equals - 6/6", tombamento.equals(tombamento_teste_3));

	}

	public void testTombamentoConstructor2() {
		final Tombamento tombamento_teste_1 = new Tombamento(123, "Localizado",
				"SubLocalTeste", "ObservaçãoTeste");
		assertEquals("Teste construtor - 1/4", tombamento_teste_1.getCodigo(),
				123);
		assertEquals(tombamento_teste_1.getSituacao(), "Localizado");
		assertEquals(tombamento_teste_1.getSublocal(), "SubLocalTeste");
		assertEquals(tombamento_teste_1.getObservacao(), "ObservaçãoTeste");

	}
}
