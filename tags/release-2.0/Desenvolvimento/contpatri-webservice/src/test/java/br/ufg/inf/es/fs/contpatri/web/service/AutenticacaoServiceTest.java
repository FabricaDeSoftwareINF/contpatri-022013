package br.ufg.inf.es.fs.contpatri.web.service;

import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia.EstrategiaDeAutenticacao;
import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * User: Halisson
 * Date: 20/09/13
 * Time: 18:57
 */
public class AutenticacaoServiceTest extends
        TestCase {
    AutenticacaoService autenticacaoService = new AutenticacaoService();

    @Test
    public void testAutenticarDeveRetornarResultadoComSucessoQuandoEstrategiaLogarComSucesso() {
        EstrategiaDeAutenticacao estrategiaDeAutenticacaoMock = mock(EstrategiaDeAutenticacao.class);
        AutenticacaoService autenticacaoService = new AutenticacaoService(estrategiaDeAutenticacaoMock);
        when(estrategiaDeAutenticacaoMock.autenticouComSucesso("loginValido", "senhaShow")).thenReturn(true);
        assertTrue(autenticacaoService.autenticar("loginValido", "senhaShow").isSucesso());
        verify(estrategiaDeAutenticacaoMock).autenticouComSucesso("loginValido", "senhaShow");
    }

    public void testAutenticarDeveRetornarResultadoSemSucessoQuandoEstrategiaLogarSemSucesso() {
        EstrategiaDeAutenticacao estrategiaDeAutenticacaoMock = mock(EstrategiaDeAutenticacao.class);
        AutenticacaoService autenticacaoService = new AutenticacaoService(estrategiaDeAutenticacaoMock);
        when(estrategiaDeAutenticacaoMock.autenticouComSucesso("loginInvalido", "senhaInvalida")).thenReturn(false);
        assertFalse(autenticacaoService.autenticar("loginInvalido", "senhaInvalida").isSucesso());
        verify(estrategiaDeAutenticacaoMock).autenticouComSucesso("loginInvalido", "senhaInvalida");
    }
}
