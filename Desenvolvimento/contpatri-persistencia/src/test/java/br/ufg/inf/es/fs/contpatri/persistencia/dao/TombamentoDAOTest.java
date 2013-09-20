/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ Instituto de
 * Informática UFG: http://www.inf.ufg.br Projeto CONTPATRI DROPBOX:
 * https://www.dropbox.com/home/CONTPATRI%20-%20012013 Projeto CONTPATRI
 * REDMINE:
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
package br.ufg.inf.es.fs.contpatri.persistencia.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.TipoTombamento;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;

/**
 * Teste da classe de persistencia de tombamentos
 */
public class TombamentoDAOTest {

	/**
	 * Tombamento
	 */
	private Tombamento tombamento;
	/**
	 * Conexao com banco de dados para Tombamento
	 */
	private TombamentoDAO tombamentoDAO;

	@Before
	/**
	 * Preparacao de dados para testes
	 */
	public void setUp() {
		tombamento = new Tombamento("12", new Date(), TipoTombamento.EXTERNO);

		tombamentoDAO = new TombamentoDAO();
	}

	@After
	/**
	 * Finalizacao dos testes
	 */
	public void tearDown() {
	}

	@Test
	/**
	 * Testa criacao e exclusao de tombamento
	 */
	public void testaCriacaoExclusaoTombamento() {
		tombamentoDAO.create(tombamento);
		Long id = tombamento.getId();
		Tombamento itemRecuperado = (Tombamento) tombamentoDAO.findByID(
				Tombamento.class, id);
		assertNotNull(itemRecuperado);

		tombamentoDAO.delete(tombamento);
		Tombamento itemExcluido = (Tombamento) tombamentoDAO.findByID(
				Tombamento.class, id);
		assertNull(itemExcluido);
		
		assertNotNull(tombamentoDAO);
	}
}