/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ 
 * Instituto de Informática UFG: http://www.inf.ufg.br 
 * Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013 
 *
 * Copyleft © UFG.
 *
 * Licenciado sobre a licença GNU-GPL v3
 *
 * Você pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html
 *
 * A menos que especificado ou exigido por legislação local, o software é
 * fornecido "da maneira que está", sem garantias ou condições de qualquer tipo,
 * nem expressas nem implícitas. Em caso de dúvidas referir a licença GNU-GPL.
 */
package br.ufg.inf.es.fs.contpatri.mobile.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

/**
 * Teste unitário tombamento
 * 
 */
public final class TombamentoTeste {
	/**
	 * Tombamento
	 */
	private Tombamento tombamento;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		tombamento = new Tombamento();
	}

	@After
	/**
	 * Tear down dos testes
	 */
	public void tearDown() {
	}

	@Test
	/**
	 * Teste  
	 */
	public void testTombamento() {
		tombamento = new Tombamento(1L, "REALOCADO", "Sala 154",
				"Movido de sala");
		long id = 2L;
		String sit = "EXTRAVIADO";
		String subLocal = "Sala 155";
		String obs = "Não encontrado";

		tombamento.setCodigo(id);
		tombamento.setSituacao(sit);
		tombamento.setSublocal(subLocal);
		tombamento.setObservacao(obs);
		tombamento.setUltimaAlteracao();
		tombamento.setUltimaAlteracao(1379199447L);

		assertEquals(id, tombamento.getCodigo());
		assertEquals(sit, tombamento.getSituacao());
		assertEquals(subLocal, tombamento.getSublocal());
		assertEquals(obs, tombamento.getObservacao());
		assertNotNull(tombamento.getUltimaAlteracao());
		assertEquals(0, tombamento.describeContents());
	}
	
	
	/*
	*//**
	 * Teste  json
	 *//*
	public void testTombamentoJSON() {
		tombamento = new Tombamento(1L, "REALOCADO", "Sala 154",
				"Movido de sala");
		long id = 2L;
		String sit = "EXTRAVIADO";
		String subLocal = "Sala 155";
		String obs = "Não encontrado";

		tombamento.setCodigo(id);
		tombamento.setSituacao(sit);
		tombamento.setSublocal(subLocal);
		tombamento.setObservacao(obs);
		tombamento.setUltimaAlteracao();
		
		String json = tombamento.toJson();

		System.out.println(json);
		assertEquals(false, json.isEmpty());
		assertEquals(tombamento, tombamento.fromJson(json));
	}*/
}
