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

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Agente;
import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Coleta;
import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.model.TipoSituacao;

/**
 * Testes unit�rios da respectiva classe de dominio
 * 
 * @author Thais
 * 
 */

public class ColetaTest {

	private static final Date EMISSAO = new Date();
	private Gestor mockGestor = new Gestor("João Bosco", "1234567",	"joaobosco@ufg.inf");
	private BemPatrimonial b1;
	private BemPatrimonial b2 = new BemPatrimonial("Mesa Quadrada",	mock(Origem.class), mock(SubLocal.class));
	private Inventario inventario;
	private List<BemPatrimonial> listaBensSoUmItem = new ArrayList<BemPatrimonial>();
	private TipoSituacao situacao = TipoSituacao.LOCALIZADO;
	private TipoSituacao situacao2 = TipoSituacao.EXTRAVIADO;

	@Test
	public void testarEquals() throws CloneNotSupportedException {
		b1 = new BemPatrimonial("Computador", mock(Origem.class),mock(SubLocal.class));

		listaBensSoUmItem.add(b1);
		listaBensSoUmItem.add(b2);

		inventario = new Inventario(EMISSAO, mockGestor, listaBensSoUmItem);

		Coleta coleta1 = new Coleta(b1, inventario, situacao);
		Coleta coleta2 = new Coleta(b1, inventario, situacao);
		Coleta coleta3 = new Coleta(b2, inventario, situacao2);

		Object o = new Object();

		assertTrue(coleta1.equals(coleta2));
		assertFalse(coleta1.equals(coleta3));

		assertFalse(coleta2.equals(o));

		assertTrue(coleta1.equals(coleta1));
		assertTrue(!o.getClass().equals(coleta1));

		assertTrue(!b1.equals(coleta1));
		assertFalse(b1.equals(coleta1));

		assertTrue(!inventario.equals(coleta1));
		assertFalse(inventario.equals(coleta1));

		assertNotNull(coleta1.hashCode());
		assertNotNull(mock(Agente.class));

	}
}