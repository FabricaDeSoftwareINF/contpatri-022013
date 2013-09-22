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
import java.util.Date;

/**
 * Ativo fixo sobre o qual se deseja ou se necessita manter controle patrimonial
 *
 * @author Emerson Jose Porfirio
 */
public class BemPatrimonial implements Serializable, Cloneable {

    /**
     * UID
     */
    private static final long serialVersionUID = -4633199957240608028L;
    /**
     * Id do bem
     */
    @Expose
    private Long id;
    
    @Expose
    private Long codigoSicop;
    
    private Analise analise;
    
    private Coleta coleta;
    
    @Expose
    private String descricao;
    
    private Inventario inventario;
    /**
     * Data de aquisicao do bem
     */
    private Date dataAquisicao;
    /**
     * Origem do bem
     */
    private Origem origem;
    /**
     * Gestor do bem
     */
    private Gestor gestor;
    /**
     * Agente
     */
    private Agente agente;
    /**
     * Tombamento do bem
     */
    private Tombamento tombamento;
    /**
     * Sublocal do bem
     */
    @Expose
    private SubLocal subLocal;

    /**
     * Construtor de um bem patrimonial
     *
     * @param descricao Descricao do bem
     *
     * @param origem Origem do bem
     *
     * @param subLocal Sublocal do bem
     */
    public BemPatrimonial(String descricao, Origem origem, SubLocal subLocal) {
        setDescricao(descricao);
        setOrigem(origem);
        setSubLocal(subLocal);
    }

    public BemPatrimonial() {
    }

    public void setSubLocal(SubLocal subLocal) {
        if (subLocal == null) {
            throw new IllegalArgumentException("O Sub Local não pode ser nulo.");
        }
        this.subLocal = subLocal;
    }

    /**
     * Obtem a data de aquisicao do bem
     *
     * @return Data de aquisicao
     */
    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    /**
     * Define a data de aquisicao do bem
     *
     * @param dataAquisicao Data de aquisicao do bem
     */
    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    /**
     * Retorna o tombamento do bem
     *
     * @return Tombamento do bem
     */
    public Tombamento getTombamento() {
        return tombamento;
    }

    /**
     * Define o tombamento do bem
     *
     * @param tombamento Tombamento do bem
     */
    public void setTombamento(Tombamento tombamento) {
        this.tombamento = tombamento;
    }

    /**
     * Obtem o id do bem
     *
     * @return Id do bem
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o id do bem
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoSicop() {
        return codigoSicop;
    }

    public void setCodigoSicop(Long codigoSicop) {
        this.codigoSicop = codigoSicop;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descricao do bem
     *
     * @param descricaoBreve Descricao breve do bem
     */
    public final void setDescricao(String descricaoBreve) {
        if (descricaoBreve == null || descricaoBreve.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A descrição não pode ser nula ou vazia");
        }
        this.descricao = descricaoBreve;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    /**
     * Obtem a origem do bem
     *
     * @return A origem do bem
     *
     * @throws CloneNotSupportedException Excecao de clonagem
     */
    public Origem getOrigem() throws CloneNotSupportedException {
        return origem.clone();
    }

    /**
     * Define a origem do bem
     *
     * @param origem Origem do bem
     */
    public final void setOrigem(Origem origem) {
        if (origem == null) {
            throw new IllegalArgumentException("A origem não pode ser nula");
        }
        this.origem = origem;
    }

    /**
     * Obtem o gestor do bem
     *
     * @return Gestor do bem
     *
     * @throws CloneNotSupportedException Excecao de clonagem
     */
    public Gestor getGestor() throws CloneNotSupportedException {
        return gestor.clone();
    }

    /**
     * Define o gestor do bem
     *
     * @param gestor Gestor do bem
     */
    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    /**
     * Obtem o agente do bem
     *
     * @return Agente do bem
     */
    public Agente getAgente() {
        return agente;
    }

    /**
     * Define o agente
     *
     * @param agente Agente do bem
     */
    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    /**
     * Obtem o sublocal do bem
     *
     * @return Sublocal do bem
     *
     * @throws CloneNotSupportedException Excecao de clonagem
     */
    public SubLocal getSubLocal() throws CloneNotSupportedException {
        return subLocal.clone();
    }

    public TipoSituacao getSituacao() {
        if (analise != null) {
            return analise.getSituacao();
        } else if (coleta != null) {
            return coleta.getSituacao();
        } else {
            return null;
        }
    }

    public void setSituacao(TipoSituacao tipoSituacao) {
        if (analise != null) {
            analise.setSituacao(tipoSituacao);
        } else {
            Analise analiseNova = new Analise(this, inventario, tipoSituacao);
            setAnalise(analiseNova);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BemPatrimonial)) {
            return false;
        }
        BemPatrimonial outroBem = (BemPatrimonial) obj;
        if (this.descricao.equals(outroBem.descricao)
                && this.origem.equals(outroBem.origem)) {
            return true;
        }
        return false;
    }

    /**
     * HashCode do bem patrimonial
     */
    @Override
    public int hashCode() {
        final int valorInicial = 5;
        final int valorIncremental = 15;
        int hash = valorInicial;
        hash = valorIncremental * hash
                + (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * Clona o bem patrimonial
     */
    @Override
    public final BemPatrimonial clone() throws CloneNotSupportedException {
        BemPatrimonial clone = (BemPatrimonial) super.clone();
        clone.setGestor(this.gestor == null ? null : this.gestor.clone());
        clone.setTombamento(this.tombamento == null ? null : this.tombamento
                .clone());
        clone.setSubLocal(this.subLocal == null ? null : this.subLocal.clone());
        return clone;
    }
}
