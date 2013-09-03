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

import br.ufg.inf.es.fs.contpatri.model.Origem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class OrigemTest {

	@Test(expected = IllegalArgumentException.class)
	public void testResumoDeOrigemNaoPodeSerNulo() {
		new Origem(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testResumoDeOrigemNaoPodeVazio() {
		new Origem("   ", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testResumoDeOrigemMaiorQue40caracteres() {
		new Origem("12345678901234567890123456789012345678901", null);
	}

	@Test
	public void testDetalheResumoPodeSerNulo() {
		Origem obj = new Origem("TESTE", null);
		assertNotNull(obj);
		assertEquals(null, obj.getDetalhe());
	}
}
