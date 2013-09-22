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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Agente;

/**
 * Testes unitários da respectiva classe de domínio
 *
 */
public class AgenteTest {
	
	@Test
	/**
	 * Testar metodo equals
	 */
	public void testarEquals() throws CloneNotSupportedException {
		Agente agente1 = new Agente("nome1", "123", "email1@email.com");
		Agente agente2 = agente1.clone();
		Agente agente3 = new Agente("nome3", "125", "email3@email.com");
		
		Object o = new Object();
		
		assertTrue(agente1.equals(agente2));
		assertFalse(agente1.equals(agente3));
		assertFalse(agente1.equals(null));
		assertFalse(agente1.equals(o));
		assertTrue(agente1.equals(agente1));
		assertNotNull(agente1.hashCode());
		assertNotNull(mock(Agente.class));
	}
}
