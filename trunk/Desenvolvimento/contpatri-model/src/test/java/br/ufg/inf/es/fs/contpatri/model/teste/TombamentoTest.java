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

import br.ufg.inf.es.fs.contpatri.model.TipoTombamento;
import br.ufg.inf.es.fs.contpatri.model.Tombamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static junit.framework.Assert.assertEquals;

/**
 * Testes unitários da respectiva classe de domínio
 *
 * @author Emerson Jose Porfirio
 */
public class TombamentoTest {


	@Test
	public void criarTombamentoValido() throws ParseException {
		Date dataTombamento = new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2013");
		Tombamento tombamento = new Tombamento("107", dataTombamento, TipoTombamento.EXTERNO);
		assertEquals("107", tombamento.getCodTombamento());
		Calendar cal = Calendar.getInstance();
		cal.setTime(tombamento.getDataTombamento());
		assertEquals(18, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(Calendar.MAY, cal.get(Calendar.MONTH));
		assertEquals(2013, cal.get(Calendar.YEAR));

	}

	@Test(expected = IllegalArgumentException.class)
	public void criarTombamentoCodigoInvalido1() throws ParseException {
		new Tombamento(null,
			new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2013"), TipoTombamento.EXTERNO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void criarTombamentoCodigoInvalido2() throws ParseException {
		new Tombamento("",
			new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2013"), TipoTombamento.EXTERNO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void criarTombamentoCodigoInvalido3() throws ParseException {
		new Tombamento("   ",
			new SimpleDateFormat("dd/MM/yyyy").parse("18/05/2013"), TipoTombamento.EXTERNO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void criarTombamentoDataInvalida1() {
		@SuppressWarnings("unused")
		Tombamento tombamento = new Tombamento("123", null, TipoTombamento.EXTERNO);
	}
}
