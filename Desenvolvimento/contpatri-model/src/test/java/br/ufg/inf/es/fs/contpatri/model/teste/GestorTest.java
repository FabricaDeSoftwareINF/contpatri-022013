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

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Origem;

import static org.junit.Assert.*;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class GestorTest {

	public static final String EMAIL = "joaobosco@ufg.inf";

	@Test
	public void testaGestorlNomeValido() {
		Gestor gestor = new Gestor("João Bosco", "123", EMAIL);
		assertEquals("João Bosco", gestor.getNome());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaGestorlNomeNaoPodeSerNulo() {
		new Gestor(null, "123", EMAIL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaGestorNomeNaoPodeSerVazio() {
		new Gestor("     ", "123", EMAIL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarMatriculaInvalidaVazia() {
		new Gestor("     ", "   ", EMAIL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testarMatriculaInvalidaNula() {
		new Gestor("     ", null, EMAIL);
	}

	@Test(expected = NumberFormatException.class)
	public void testarMatriculaInvalidaNaoNumerica1() {
		new Gestor("João Bosco", "ABC", EMAIL);
	}

	@Test(expected = NumberFormatException.class)
	public void testarMatriculaInvalidaNaoNumerica2() {
		new Gestor("João Bosco", "123 456 789", EMAIL);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testaGestorNomeMaiorQue60caracteres() {
		new Gestor("1234567890123456789012345678901234567890123456789012345678901", "123", EMAIL);
	}

	@Test
	public void testaEqualsFalseOutraClasse() {
		Origem origem = new Origem("teste", "teste");
		Gestor gestor = new Gestor("Teste", "00000", EMAIL);
		assertFalse(gestor.equals(origem));
	}

	@Test
	public void testaEqualsMesmaClasseFalse() {
		Gestor outroNome = new Gestor("Falso", "00000", EMAIL);
		Gestor outraMatricula = new Gestor("Teste", "11111", EMAIL);
		Gestor gestor = new Gestor("Teste", "00000", EMAIL);
		assertFalse(gestor.equals(outroNome));
		assertFalse(gestor.equals(outraMatricula));
	}

	@Test
	public void testaEqualsMesmaClasseTrue() {
		Gestor outro = new Gestor("Teste", "00000", EMAIL);
		Gestor gestor = new Gestor("Teste", "00000", EMAIL);
		assertTrue(gestor.equals(outro));
	}
}
