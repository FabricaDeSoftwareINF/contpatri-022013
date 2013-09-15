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

import br.ufg.inf.es.fs.contpatri.model.Analise;
import br.ufg.inf.es.fs.contpatri.model.Coleta;
import br.ufg.inf.es.fs.contpatri.web.model.Exportacao;

/**
 * 
 * Teste da classe Exportacao
 *
 */
public class ExportacaoTest {
	private Exportacao exportacao;
	private Set<Analise> analises;
    private Set<Coleta> coletas;

	@Before
	/**
	 * Preparacao dos testes
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		analises = new HashSet<Analise>();
		coletas = new HashSet<Coleta>();
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
	 * Testa os metodos da classe Exportacao usando construtor vazio
	 */
	public void testExportacaoSemParametros() {

		exportacao = new Exportacao();

		assertNull(exportacao.getAnalises());
		assertNull(exportacao.getColetas());
		assertNull(exportacao.getIdInventario());
	}
	
	@Test
	/**
	 * Testa os metodos da classe Exportacao passando parâmetros
	 */
	public void testExportacaoComParametros() {
		Long idInventario = 1L;
		
		exportacao = new Exportacao(idInventario, analises, coletas);
		
		assertNotNull(exportacao.getAnalises());
		assertNotNull(exportacao.getColetas());
		assertNotNull(exportacao.getIdInventario());
	}
	
	@Test
	/**
	 * Testa os metodos "set" da classe Exportacao
	 */
	public void testExportacaoMetodos() {
		exportacao = new Exportacao();
		
		exportacao.setIdInventario(1L);
		exportacao.setAnalises(analises);
		exportacao.setColetas(coletas);

		assertEquals(analises, exportacao.getAnalises());
		assertEquals(coletas, exportacao.getColetas());
		assertEquals(Long.valueOf(1L), exportacao.getIdInventario());
	}

}
