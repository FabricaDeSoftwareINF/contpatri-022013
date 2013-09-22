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

import br.ufg.inf.es.fs.contpatri.model.Gestor;
import br.ufg.inf.es.fs.contpatri.model.interfaces.IUsuario;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.GestorDAO;
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
public class GestorBean implements Serializable, IUsuarioBean {

    private List<IUsuario> usuarios;
    private IUsuario usuario;
    private IUsuario usuarioSelecionado;
    private GestorDAO gestorDAO;

    /**
     * Creates a new instance of GestorManagedBean
     */
    public GestorBean() {
        gestorDAO = new GestorDAO();
        usuario = new Gestor();
        usuarioSelecionado = new Gestor();
        usuarios = gestorDAO.findAll(Gestor.class);
    }

    /**
     * Adiciona o gestor ao banco de dados.
     */
    @Override
    public void adicionar() {
        gestorDAO.create((Gestor) usuario);
        usuario = new Gestor();
        usuarios = gestorDAO.findAll(Gestor.class);
    }

    /**
     * Atualiza o gestor selecionado no banco de dados
     */
    @Override
    public void editar() {
        gestorDAO.update((Gestor) usuarioSelecionado);
        usuarios = gestorDAO.findAll(Gestor.class);
    }

    /**
     * Exclui o gestor selecionado do banco de dados
     */
    @Override
    public void excluir() {
        gestorDAO.delete((Gestor) usuarioSelecionado);
        usuarios = gestorDAO.findAll(Gestor.class);
    }

    /**
     *
     * @return gestor preenchido na tela de cadastro
     */
    @Override
    public IUsuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario gestor preenchido na tela de cadastro
     */
    @Override
    public void setUsuario(IUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return gestor atualmente selecionado na tabela
     */
    @Override
    public IUsuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    /**
     *
     * @param usuarioSelecionado gestor atualmente selecionado na tabela
     */
    @Override
    public void setUsuarioSelecionado(IUsuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    /**
     *
     * @return lista de todos os gestores cadastrados
     */
    @Override
    public List<IUsuario> getUsuarios() {
        return usuarios;
    }
}
