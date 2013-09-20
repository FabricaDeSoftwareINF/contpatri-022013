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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Gestor;

/**
 * Teste da classe de persistencia de gestores
 */
public class GestorDAOTest {

	/**
	 * Gestor
	 */
	private Gestor gestor;
	/**
	 * Conexao com banco de dados para Gestor
	 */
	private GestorDAO gestorDAO;

	@Before
	/**
	 * Preparacao de dados para testes
	 */
	public void setUp() {

		gestor = new Gestor("Gestor teste 50", "540548",
				"email6t7@email.ufg.br");
		gestor.setSenha("ol12ih391");

		gestorDAO = new GestorDAO();
	}

	@After
	/**
	 * Finalizacao dos testes
	 */
	public void tearDown() {
	}

	@Test
	/**
	 * Testa criacao e exclusao de gestor
	 */
	public void testaCriacaoExclusaoGestor() {
		gestorDAO.create(gestor);
		Long id = gestor.getId();
		Gestor itemRecuperado = (Gestor) gestorDAO.findByID(Gestor.class, id);
		assertEquals(gestor, itemRecuperado);

		gestorDAO.delete(gestor);
		Gestor itemExcluido = (Gestor) gestorDAO.findByID(Gestor.class, id);
		assertNull(itemExcluido);

		assertNotNull(gestorDAO);
	}
}