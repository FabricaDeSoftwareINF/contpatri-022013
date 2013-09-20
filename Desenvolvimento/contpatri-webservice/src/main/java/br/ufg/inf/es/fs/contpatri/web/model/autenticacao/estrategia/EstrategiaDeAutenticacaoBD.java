package br.ufg.inf.es.fs.contpatri.web.model.autenticacao.estrategia;

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.GestorDAO;

/**
 * User: Halisson
 * Date: 19/09/13
 * Time: 20:51
 */
public class EstrategiaDeAutenticacaoBD implements EstrategiaDeAutenticacao {
    @Override
    public boolean autenticouComSucesso(String login, String senha) {
        // TODO: Alterar módulo de persistência para permitir busca no GestorDAO por email e senha
        GestorDAO gestorDAO = new GestorDAO();
        Gestor gestor = gestorDAO.findByEmail(login);
        return (gestor != null && gestor.getSenha().equals(senha));
    }
}
