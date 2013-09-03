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
import java.util.List;

/**
 *
 * @author Guilherme de Paula
 */
interface IUsuarioBean {

    public void adicionar();

    public void editar();

    public void excluir();
    
    public IUsuario getUsuario();
    
    public void setUsuario(IUsuario usuario);
    
    public IUsuario getUsuarioSelecionado();
    
    public void setUsuarioSelecionado(IUsuario usuarioSelecionado);
    
    public List<IUsuario> getUsuarios();
}
