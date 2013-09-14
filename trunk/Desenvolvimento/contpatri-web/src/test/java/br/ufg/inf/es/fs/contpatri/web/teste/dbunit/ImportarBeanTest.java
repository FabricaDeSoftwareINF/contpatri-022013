package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.UploadedFile;

import br.ufg.inf.es.fs.contpatri.web.controller.ImportarBean;

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
	 * Teste
	 */
	public void testImportarBean() {
		String msg = "Mensagem Teste";
		UploadedFile upFile = null;
		
		importarBean.setMensagem(msg);
		importarBean.setArquivo(upFile);
		
		assertNull(importarBean.getArquivo());
		assertEquals(msg, importarBean.getMensagem());
	}
}
