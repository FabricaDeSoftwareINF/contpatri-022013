/*
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
package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.web.model.Importacao;

/**
 * 
 * Teste da classe Importacao
 *
 */
public class ImportacaoTest {
	private Importacao importacao;
	private Set<BemPatrimonial> bensPatrimoniais;

	@Before
	/**
	 * Preparacao dos testes
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		bensPatrimoniais = new HashSet<BemPatrimonial>();
	}

	@After
	/**
	 * Finalizacao dos testes
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * Testa os metodos da classe Importacao usando construtor vazio
	 */
	public void testImportacaoSemParametros() {

		importacao = new Importacao();

		assertNull(importacao.getBensPatrimoniais());
		assertNull(importacao.getIdInventario());
	}
	
	@Test
	/**
	 * Testa os metodos da classe Importacao passando parâmetros
	 */
	public void testImportacaoComParametros() {
		Long idInventario = 1L;
		
		importacao = new Importacao(idInventario, bensPatrimoniais);
		
		assertNotNull(importacao.getBensPatrimoniais());
		assertNotNull(importacao.getIdInventario());
	}
	
	@Test
	/**
	 * Testa os metodos "set" da classe Importacao
	 */
	public void testImportacaoMetodos() {
		importacao = new Importacao();
		
		importacao.setIdInventario(1L);
		importacao.setBensPatrimoniais(bensPatrimoniais);

		assertEquals(bensPatrimoniais, importacao.getBensPatrimoniais());
		assertEquals(Long.valueOf(1L), importacao.getIdInventario());
	}

}
