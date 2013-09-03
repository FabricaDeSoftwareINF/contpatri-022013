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

import br.ufg.inf.es.fs.contpatri.persistencia.dao.AgenteDAO;
import br.ufg.inf.es.fs.contpatri.model.Agente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme de Paula
 */
public class AgenteDAOTest {

    private Agente agente;
    private AgenteDAO agenteDAO;

    public AgenteDAOTest() {
        agente = new Agente("Guilherme", "12345", "guilhermepaula@inf.ufg.br");
        agente.setSenha("123456");
        agenteDAO = new AgenteDAO();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaCriacaoExclusaoAgente() {
        agenteDAO.create(agente);
        Long id = agente.getId();
        Agente agenteRecuperado = agenteDAO.findByID(Agente.class, id);
        assertEquals(agente, agenteRecuperado);
        
        agenteDAO.delete(agente);
        Agente agenteExcluido = agenteDAO.findByID(Agente.class, id);
        assertNull(agenteExcluido);
    }
}