package br.ufg.inf.es.fs.contpatri.web.controller.builder;

import br.ufg.inf.es.fs.contpatri.web.model.autenticacao.Autenticacao;

public class AutenticacaoJsonBuilder extends JsonBuilder<Autenticacao> {
    @Override
    Class<Autenticacao> getClazz() {
        return Autenticacao.class;
    }
}
