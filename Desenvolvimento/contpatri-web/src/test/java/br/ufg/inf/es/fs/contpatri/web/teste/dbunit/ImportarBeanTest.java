/*
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
package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.UploadedFile;

import br.ufg.inf.es.fs.contpatri.web.controller.ImportarBean;

/**
 * 
 * Teste Importar Bean
 *
 */
public class ImportarBeanTest {
	/**
	 * Importar Bean
	 */
	private ImportarBean importarBean;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		importarBean = new ImportarBean();
	}

	@Test
	/**
	 * Teste arquivo null
	 */
	public void testImportarNull() {
		UploadedFile upFile = null;
		String msg = "Ocorreu um erro na importação do arquivo: " + upFile;
		
		importarBean.setArquivo(upFile);
		importarBean.upload();
		
		assertNull(importarBean.getArquivo());
		assertEquals(msg, importarBean.getMensagem());
	}
	
	@Test
	/**
	 * Teste mensagem
	 */
	public void testMensagem() {
		String msg = "Mensagem Teste";
		UploadedFile upFile = null;
		
		importarBean.setMensagem(msg);
		importarBean.setArquivo(upFile);
		
		assertNull(importarBean.getArquivo());
		assertEquals(msg, importarBean.getMensagem());
	}
}
