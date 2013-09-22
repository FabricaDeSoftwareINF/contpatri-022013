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

import br.ufg.inf.es.fs.contpatri.model.SubLocal;

import static junit.framework.Assert.assertEquals;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class SubLocalTest {

	@Test
	public void testarLocalValido() {
		SubLocal local = new SubLocal("Direção");
		assertEquals("Direção", local.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarLocalInvalido1() {
		new SubLocal(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarLocalInvalido2() {
		new SubLocal("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarLocalInvalido3() {
		new SubLocal(" ");
	}
}
