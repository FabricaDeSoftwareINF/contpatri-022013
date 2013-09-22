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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;

/**
 * Teste da classe de persistencia de bens patrimoniais
 */
public class BemPatrimonialDAOTest {

	private GenericDAO<Object, Long> genericDAO = new GenericDAOImpl<Object, Long>() {
	};

	/**
	 * Origem
	 */
	private Origem origem;

	/**
	 * SubLocal
	 */
	private SubLocal subLocal;

	/**
	 * BemPatrimonial
	 */
	private BemPatrimonial bemPatrimonial;
	/**
	 * Conexao com banco de dados para BemPatrimonial
	 */
	private BemPatrimonialDAO bemPatrimonialDAO;

	@Before
	/**
	 * Preparacao de dados para testes
	 */
	public void setUp() {
		origem = new Origem("Resumo", "Detalhe");
		subLocal = new SubLocal("Sala 101");

		genericDAO.create(origem);
		genericDAO.create(subLocal);

		bemPatrimonial = new BemPatrimonial("Cadeira", origem, subLocal);
		bemPatrimonial.setCodigoSicop(1L);
		List<BemPatrimonial> bens = new ArrayList<BemPatrimonial>();
		bens.add(bemPatrimonial);

		bemPatrimonialDAO = new BemPatrimonialDAO();
	}

	@After
	/**
	 * Finalizacao dos testes
	 */
	public void tearDown() {
		try {
			genericDAO.delete(origem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			genericDAO.delete(subLocal);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	/**
	 * Testa criacao e exclusao de bemPatrimonial
	 */
	public void testaCriacaoExclusaoBemPatrimonial() {
		try {
			bemPatrimonialDAO.create(bemPatrimonial);
			Long id = bemPatrimonial.getId();
			BemPatrimonial itemRecuperado = (BemPatrimonial) bemPatrimonialDAO
					.findByID(BemPatrimonial.class, id);
			assertNotNull(itemRecuperado);

			bemPatrimonialDAO.delete(bemPatrimonial);
			BemPatrimonial itemExcluido = (BemPatrimonial) bemPatrimonialDAO
					.findByID(BemPatrimonial.class, id);
			assertNull(itemExcluido);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertNotNull(bemPatrimonialDAO);
	}
}