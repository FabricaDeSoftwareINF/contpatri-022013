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
package br.ufg.inf.es.fs.contpatri.persistencia;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import br.ufg.inf.es.fs.contpatri.model.Gestor;

/**
 * 
 * Teste do JsonUtils
 *
 */
public class JsonUtilTest {

	@Test
	/**
	 * Testa as conversões de JSON
	 * @throws IOException
	 */
	public void testJsonUtils() throws IOException {
		JsonUtil jsonUtil = new JsonUtil();
		Gestor objeto = new Gestor("Nome", "32135156", "Email");
		
		InputStream input = jsonUtil.deJsonParaInputStream(objeto);

		assertNotNull(jsonUtil.deInputStreamParaJson(input));
	}

}
