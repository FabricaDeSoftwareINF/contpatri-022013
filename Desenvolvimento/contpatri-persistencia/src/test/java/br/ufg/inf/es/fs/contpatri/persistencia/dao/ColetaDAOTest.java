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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Coleta;
import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.model.TipoSituacao;

/**
 * Teste da classe de persistencia de coletas
 */
public class ColetaDAOTest {

	/**
	 * Dao teste
	 */
	private static GenericDAO<Object, Long> genericDAO = new GenericDAOImpl<Object, Long>() {
	};

	/**
	 * Gestor
	 */
	private Gestor gestor;

	/**
	 * Origem
	 */
	private Origem origem;

	/**
	 * SubLocal
	 */
	private SubLocal subLocal;

	/**
	 * Inventario
	 */
	private Inventario inventario;
	/**
	 * Conexao com banco de dados para Inventario
	 */
	private InventarioDAO inventarioDAO;

	/**
	 * Coleta
	 */
	private Coleta coleta;
	/**
	 * Conexao com banco de dados para Coleta
	 */
	private ColetaDAO coletaDAO;

	@Before
	/**
	 * Preparacao de dados para testes
	 */
	public void setUp() {
		origem = new Origem("Resumo2", "Detalhe2");
		subLocal = new SubLocal("Sala 104");

		BemPatrimonial bemPatrimonial = new BemPatrimonial("Cadeira", origem,
				subLocal);
		List<BemPatrimonial> bens = new ArrayList<BemPatrimonial>();
		bens.add(bemPatrimonial);

		gestor = new Gestor("Gestor teste 50", "54538", "email65g@email.ufg.br");
		gestor.setSenha("ol12ih391");

		inventario = new Inventario(new Date(), gestor, bens);

		coleta = new Coleta(bemPatrimonial, inventario, TipoSituacao.LOCALIZADO);
		coletaDAO = new ColetaDAO();
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
		try {
			genericDAO.delete(gestor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	/**
	 * Testa criacao e exclusao de coleta
	 */
	public void testaCriacaoExclusaoColeta() {
		try {
			genericDAO.create(gestor);
			genericDAO.create(origem);
			genericDAO.create(subLocal);
			coletaDAO.create(coleta);

			Long id = coleta.getId();
			Coleta itemRecuperado = (Coleta) coletaDAO.findByID(Coleta.class,
					id);
			assertEquals(coleta, itemRecuperado);

			coletaDAO.delete(coleta);
			Coleta itemExcluido = (Coleta) coletaDAO.findByID(Coleta.class, id);
			assertNull(itemExcluido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		assertNotNull(coletaDAO);
	}
}