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

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;
import static junit.framework.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class BemPatrimonialTest {

	public static final Origem ORIGEM = mock(Origem.class);
        public static final SubLocal SUBLOCAL = mock(SubLocal.class);

    @Test
    public void testarBemPatrimonialValido() throws CloneNotSupportedException {
        Origem origem = new Origem("CNPQ", "Fundo de Descentralização do MEC");
        SubLocal subLocal = new SubLocal("Sala 6");
        BemPatrimonial bem = new BemPatrimonial("Computador DELL",
                origem, subLocal);
        assertEquals("Computador DELL", bem.getDescricao());
        assertNotNull(bem.getOrigem());
        assertNotNull(bem.getSubLocal());
        assertNull(bem.getTombamento());

    }

    @Test(expected = IllegalArgumentException.class)
    public void criarBemPatrimonialSemDescricao() {
        new BemPatrimonial(null, ORIGEM, SUBLOCAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void criarBemPatrimonialDescricaoVazia() {
        new BemPatrimonial("   ", ORIGEM, SUBLOCAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void criarBemPatrimonialSemOrigem() {
        new BemPatrimonial("Computador", null, SUBLOCAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void criarBemPatrimonialSemLocal() {
        new BemPatrimonial("Computador", ORIGEM, null);
    }

    @Test
    public void testarBemPatrimonialComTombamento() {
        BemPatrimonial bem = new BemPatrimonial("Computador", ORIGEM, SUBLOCAL);
        Tombamento tombamento = mock(Tombamento.class);
        bem.setTombamento(tombamento);
        assertEquals(tombamento, bem.getTombamento());
    }

    @Test
    public void testarBemPatrimonialCloneGestor() throws CloneNotSupportedException {
        Gestor resp = new Gestor("Emerson", "123456", "emerson@ufg.inf.br");
        BemPatrimonial bem = new BemPatrimonial("Computador", ORIGEM, SUBLOCAL);
        bem.setGestor(resp);
        assertTrue(resp != bem.getGestor());
        assertEquals(resp, bem.getGestor());
    }

    @Test
    public void testarBemPatrimonialCloneOrigem() throws CloneNotSupportedException {
        Origem origem = new Origem("CNPQ", "Fundo de Descentralização");
        BemPatrimonial bem = new BemPatrimonial("Computador", origem, SUBLOCAL);
        assertTrue(origem != bem.getOrigem());
        assertEquals(origem, bem.getOrigem());
    }

    @Test
    public void testarBemPatrimonialCloneSubLocal() throws CloneNotSupportedException {
        SubLocal local = new SubLocal("Sala 6");
        BemPatrimonial bem = new BemPatrimonial("Computador DELL",
                ORIGEM, local);
        assertTrue(local != bem.getSubLocal());
        assertEquals(local, bem.getSubLocal());
    }
}