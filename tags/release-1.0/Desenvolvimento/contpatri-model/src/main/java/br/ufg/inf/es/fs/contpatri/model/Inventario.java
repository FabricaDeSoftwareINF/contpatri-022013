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
package br.ufg.inf.es.fs.contpatri.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.*;

/**
 * Representa um processo de tomada de contas dos bens patrimoniais da Unidade
 * da UFG.
 *
 * @author Emerson Jose Porfirio
 */
public class Inventario implements Serializable {

    /**
     * UID
     */
    private static final long serialVersionUID = 7539830852103276098L;
    /**
     * Id do inventario
     */
    @Expose
    private Long id;
    /**
     * Data de emissao
     */
    @Expose
    private Date dataEmissao;
    /**
     * Data de fechamento
     */
    @Expose
    private Date dataFechamento;
    /**
     * Gestor do inventario
     */
    private Gestor gestor;
    
    @Expose
    private Set<Analise> analises = new HashSet<Analise>();
    
    @Expose
    private Set<Coleta> coletas = new HashSet<Coleta>();
    /**
     * Inventarios analisados
     */
    private Set<Analise> analisados = new HashSet<Analise>();

    /**
     * Construtor da classe
     */
    public Inventario() {
    }

    /**
     * Construtor da classe inventario
     *
     * @param dataEmissao Data de emissao do inventario
     *
     * @param gestor Gestor do inventario
     *
     * @param bensPatrimoniais Bens patrimoniais do inventario
     */
    public Inventario(Date dataEmissao, Gestor gestor,
            List<BemPatrimonial> bensPatrimoniais) {
        setDataEmissao(dataEmissao);
        setGestor(gestor);
        adicionarAnalisados(bensPatrimoniais);
    }

    /**
     * Adiciona os bens analisados
     *
     * @param bensPatrimoniais Bens patrimoniais
     */
    private void adicionarAnalisados(List<BemPatrimonial> bensPatrimoniais) {
        if (bensPatrimoniais == null || bensPatrimoniais.isEmpty()) {
            throw new IllegalArgumentException(
                    "A lista de bens analisados deve ter ao menos um elemento.");
        }
        for (BemPatrimonial bem : bensPatrimoniais) {
            this.adicionarAnalisado(bem);
        }
    }

    /**
     * Adicionar um bem analisado
     *
     * @param bem Bem analisado
     */
    public final void adicionarAnalisado(BemPatrimonial bem) {
        this.analisados.add(new Analise(bem, this, null));
    }

    /**
     * Remove um bem analisado
     *
     * @param bem Bem analisado
     *
     * @return <true> Bem existente e removido <false> Bem não existe
     */
    public boolean removerAnalisado(BemPatrimonial bem) {
        for (Analise analisado : this.analisados) {
            if (analisado.getBemPatrimonial().equals(bem)) {
                this.analisados.remove(analisado);
                return true;
            }
        }
        return false;
    }

    /**
     * Define o gestor do inventario
     *
     * @param gestor Gestor do inventario
     */
    public void setGestor(Gestor gestor) {
        if (gestor == null) {
            throw new IllegalArgumentException("Gestor não pode ser nulo.");
        }
        this.gestor = gestor;
    }

    /**
     * Define a data de emissao do inventario
     *
     * @param dataEmissao Data de emissao do inventario
     */
    public void setDataEmissao(Date dataEmissao) {
        if (dataEmissao == null) {
            throw new IllegalArgumentException("A data de "
                    + "emissão não pode ser nula");
        }
        this.dataEmissao = dataEmissao;
    }

    /**
     * Define a data de fechamento
     *
     * @param dataFechamento Data de fechamento
     */
    public void setDataFechamento(Date dataFechamento) {
        if (dataFechamento != null && dataFechamento.before(dataEmissao)) {
            throw new IllegalArgumentException(
                    "A data fechamento não pode ser "
                    + "nula ou anterior à data inicial");
        }
        this.dataFechamento = dataFechamento;
    }

    /**
     * Obtem a id do inventario
     *
     * @return Id do inventario
     */
    public Long getId() {
        return id;
    }

    /**
     * Define a id do inventario
     *
     * @param id Id do inventario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtem a data de emissao do inventario
     *
     * @return Data de emissao do inventario
     */
    public Date getDataEmissao() {
        return (Date) this.dataEmissao.clone();
    }

    /**
     * Obtem a data de fechamento
     *
     * @return Data de fechamento do inventario
     */
    public Date getDataFechamento() {
        if (this.dataFechamento != null) {
            return (Date) this.dataFechamento.clone();
        }
        return this.dataFechamento;
    }

    /**
     * Obtem o gestor do inventario
     *
     * @return Gestor do inventario
     *
     * @throws CloneNotSupportedException Excecao de clonagem
     */
    public Gestor getGestor() throws CloneNotSupportedException {
        return gestor.clone();
    }

    /**
     * Obtem os bem analisados
     *
     * @return Itens analisados
     */
    public Set<Analise> getAnalisados() {
        return Collections.unmodifiableSet(analisados);
    }

    /**
     * Indica se o inventario pode ser encerrado
     *
     * @return <true> Inventario pode ser encerrado <false> Inventario nao pode
     * ser encerrado
     */
    public boolean podeSerEncerrado() {
        return todasAnalisesFeitas() && !getEncerrado();
    }

    /**
     * Indica se todas as analises foram feitas
     *
     * @return <true> Todas as analises foram feitas <false> Existem analises
     * pendentes
     */
    public boolean todasAnalisesFeitas() {
        for (Analise c : analisados) {
            if (c.getSituacao() == null) {
                return false;
            }
        }
        return true;
    }

    public Set<Coleta> getColetas() {
        return coletas;
    }

    public void setColetas(Set<Coleta> coletas) {
        this.coletas = coletas;
    }
    

    public Set<Analise> getAnalises() {
        return analises;
    }

    public void setAnalises(Set<Analise> analises) {
        this.analises = analises;
    }

    public boolean getEncerrado() {
        return dataFechamento != null;
    }
}
