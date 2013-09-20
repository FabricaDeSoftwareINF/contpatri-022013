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
package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.Agente;
import br.ufg.inf.es.fs.contpatri.model.interfaces.IUsuario;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.AgenteDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Guilherme de Paula
 */
@ManagedBean
@ViewScoped
public class AgenteBean implements Serializable, IUsuarioBean {

    private List<IUsuario> usuarios;
    private IUsuario usuario;
    private IUsuario usuarioSelecionado;
    private AgenteDAO agenteDAO;

    /**
     * Creates a new instance of AgenteManagedBean
     */
    public AgenteBean() {
        agenteDAO = new AgenteDAO();
        usuario = new Agente();
        usuarioSelecionado = new Agente();
        usuarios = agenteDAO.findAll(Agente.class);
    }

    /**
     * Adiciona o agente ao banco de dados.
     */
    @Override
    public void adicionar() {
        agenteDAO.create((Agente) usuario);
        usuario = new Agente();
        usuarios = agenteDAO.findAll(Agente.class);
    }

    /**
     * Atualiza o agente selecionado no banco de dados
     */
    @Override
    public void editar() {
        agenteDAO.update((Agente) usuarioSelecionado);
        usuarios = agenteDAO.findAll(Agente.class);
    }

    /**
     * Exclui o agente selecionado do banco de dados
     */
    @Override
    public void excluir() {
        agenteDAO.delete((Agente) usuarioSelecionado);
        usuarios = agenteDAO.findAll(Agente.class);
    }

    /**
     *
     * @return agente preenchido na tela de cadastro
     */
    @Override
    public IUsuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario agente preenchido na tela de cadastro
     */
    @Override
    public void setUsuario(IUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return agente atualmente selecionado na tabela
     */
    @Override
    public IUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    /**
     *
     * @param usuarioSelecionado agente atualmente selecionado na tabela
     */
    @Override
    public void setUsuarioSelecionado(IUsuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    /**
     *
     * @return lista de todos os agentes cadastrados
     */
    @Override
    public List<IUsuario> getUsuarios() {
        return usuarios;
    }
}
