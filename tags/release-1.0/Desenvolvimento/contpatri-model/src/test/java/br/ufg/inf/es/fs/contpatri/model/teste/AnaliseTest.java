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

import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Analise;
import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Inventario;
import br.ufg.inf.es.fs.contpatri.model.TipoSituacao;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class AnaliseTest {

	@Test
	public void criaAnaliseValido() {
		Inventario inventario = mock(Inventario.class);
		BemPatrimonial bemPatrimonial = mock(BemPatrimonial.class);
		Analise analise = new Analise(bemPatrimonial, inventario, null);
		assertEquals(inventario, analise.getInventario());
		assertEquals(bemPatrimonial, analise.getBemPatrimonial());
		assertEquals(null, analise.getSituacao());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarBemPatrimonialNulo() {
		Inventario inventario = mock(Inventario.class);
		new Analise(null, inventario,
			TipoSituacao.LOCALIZADO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarInventarioNulo() {
		BemPatrimonial bemPermanente = mock(BemPatrimonial.class);
		new Analise(bemPermanente, null,
			TipoSituacao.LOCALIZADO);
	}
}
