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
package br.ufg.inf.es.fs.contpatri.model.teste;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Analise;
import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class InventarioTest {

	private static final Date EMISSAO = new Date();
	private Gestor mockGestor;
	private BemPatrimonial b1, b2;
	private List<BemPatrimonial> listaBensSoUmItem = new ArrayList<BemPatrimonial>();

	@Before
	public void setUp() {
		mockGestor = new Gestor("João Bosco", "1234567", "joaobosco@ufg.inf");
		b1 = new BemPatrimonial("Computador", mock(Origem.class), mock(SubLocal.class));
		b2 = new BemPatrimonial("Mesa Quadrada", mock(Origem.class), mock(SubLocal.class));
		listaBensSoUmItem.add(b1);
	}

	@Test
	public void testarInventarioValido() throws CloneNotSupportedException {
		Inventario inventario = new Inventario(EMISSAO, mockGestor, listaBensSoUmItem);
		assertNull(inventario.getDataFechamento());
		Date dataFechamento = new Date();
		inventario.setDataFechamento(dataFechamento);
		assertEquals(EMISSAO, inventario.getDataEmissao());
		DateUtils.isSameDay(EMISSAO, inventario.getDataFechamento());
		assertTrue(inventario.getDataFechamento() != dataFechamento);
		assertEquals(mockGestor, inventario.getGestor());
		assertTrue(inventario.getAnalisados().contains(new Analise(b1, inventario, null)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarInventarioSemDataEmissao() {
		new Inventario(null, mockGestor, listaBensSoUmItem);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarInventarioSemGestor() {
		new Inventario(EMISSAO, null, listaBensSoUmItem);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarInventarioSemBensConferidos() {
		new Inventario(EMISSAO, mockGestor, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarInventarioConferidosVazio() {
		new Inventario(EMISSAO, mockGestor, new ArrayList<BemPatrimonial>());
	}

	@Test
	public void testarInventarioCloneGestor() throws CloneNotSupportedException {
		Gestor resp = new Gestor("João Bosco", "123456", "joaobosco@ufg.inf");
		Inventario inventario = new Inventario(EMISSAO, resp, listaBensSoUmItem);
		assertTrue(resp != inventario.getGestor());
		assertEquals(resp, inventario.getGestor());
	}

	@Test
	public void testarRemoverBemConferido() {
		Inventario inventario = new Inventario(EMISSAO, mockGestor, listaBensSoUmItem);
		inventario.adicionarAnalisado(b2);
		assertEquals(2, inventario.getAnalisados().size());
		inventario.removerAnalisado(b1);
		assertFalse(inventario.getAnalisados().contains(new Analise(b1, inventario, null)));
		assertTrue(inventario.getAnalisados().contains(new Analise(b2, inventario, null)));
	}
}