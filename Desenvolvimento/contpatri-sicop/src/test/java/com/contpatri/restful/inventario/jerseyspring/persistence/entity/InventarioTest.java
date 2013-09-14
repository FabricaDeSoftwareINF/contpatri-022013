package com.contpatri.restful.inventario.jerseyspring.persistence.entity;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class InventarioTest {
	/**
	 * Inventário
	 */
	private Inventario inventario;

	@Test
	/**
	 * Teste o simulador de inventário do sicop
	 */
	public void testInventario() {
		Long id = 1L;
		String description = "Computador";
		String name = "1";
		String url = "http://200.137.197.236:3306/contpatri";
		
		inventario = new Inventario();
		inventario.setId(id);
		inventario.setDescription(description);
		inventario.setName(name);
		inventario.setUrl(url);
		
		assertEquals(id, inventario.getId());
		assertEquals(description, inventario.getDescription());
		assertEquals(name, inventario.getName());
		assertEquals(url, inventario.getUrl());
	}

}
