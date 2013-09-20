package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;

import static org.junit.Assert.*;

import java.lang.reflect.GenericDeclaration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.SortOrder;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.model.Origem;
import br.ufg.inf.es.fs.contpatri.model.SubLocal;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.*;
import br.ufg.inf.es.fs.contpatri.web.controller.BemPatrimonialDataModel;

public class BemPatrimonialDataModelTest {
	/**
	 * Bem patrimonial
	 */
	private BemPatrimonial bemPatrimonial;
	/**
	 * Bem patrimonial web data
	 */
	private BemPatrimonialDataModel bpdm;
	/**
	 * Dao para bem patrimonial
	 */
	private BemPatrimonialDAO daoBemPatrimonial;
	

	@Before
	/**
	 * Dados de preparação para testes
	 */
	public void setUp() {
		bpdm = new BemPatrimonialDataModel();
		Origem origem = new Origem("Resumo", "Detalhe");
		origem.setId(1L);
		SubLocal subLocal = new SubLocal("Sala 154");
		subLocal.setId(1L);
		bemPatrimonial = new BemPatrimonial("Computador", origem, subLocal);
		bemPatrimonial.setCodigoSicop(1L);
		bemPatrimonial.setDataAquisicao(new Date());
		
		daoBemPatrimonial = new BemPatrimonialDAO();
		//daoBemPatrimonial.create(bemPatrimonial);
	}

	//@Test
	/**
	 * Teste do método load
	 */
	public void testLoad() {
		Map<String, String> filters = new HashMap<String, String>(); 
		filters.put("descricao", "Computador");
		List<BemPatrimonial> list = bpdm.load(1, 10, "idInventario", SortOrder.ASCENDING, filters);

		System.out.println(list);
		assertNotNull(list);
		
		daoBemPatrimonial.delete(bemPatrimonial);
	}
	
	@Test
	/**
	 * Teste do método getRowKey
	 */
	public void testGetRowKey() {
		bemPatrimonial.setId(1L);
		assertEquals(1L, bpdm.getRowKey(bemPatrimonial));
	}
	
	

}
