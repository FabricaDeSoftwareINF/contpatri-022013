package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.web.controller.RelatoriosBean;

public class RelatoriosBeanTest {
	/**
	 * Relatorios Bean
	 */
	private RelatoriosBean relatoriosBean;

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		relatoriosBean = new RelatoriosBean();
	}

	@Test
	/**
	 * Teste
	 */
	public void testRelatoriosBean() {
		BemPatrimonial bemPatrimonialSelecionado = new BemPatrimonial("Descrição", new Origem(), new SubLocal());
		
		relatoriosBean.setBemPatrimonialSelecionado(bemPatrimonialSelecionado);
		relatoriosBean.setBemPatrimonialDataModel(relatoriosBean.getBemPatrimonialDataModel());
		
		assertEquals(bemPatrimonialSelecionado, relatoriosBean.getBemPatrimonialSelecionado());
		assertNotNull(relatoriosBean.getTiposAnalise());
	}
}
