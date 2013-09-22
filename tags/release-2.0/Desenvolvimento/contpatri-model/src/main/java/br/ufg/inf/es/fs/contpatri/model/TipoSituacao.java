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

/**
 * Tipos de situações ocorridas na coleta ou análise de um bem patrimonial: LOCALIZADO -
 * bem encontrado no SubLocal estabelecido no Inventario REALOCADO - bem
 * encontrado em SubLocal que não o estabelecido no Inventario EXTRAVIADO - bem
 * não encontrado em SubLocal estabelecido no Inventario
 */
public enum TipoSituacao {

    LOCALIZADO("Localizado"), REALOCADO("Realocado"), EXTRAVIADO(
    "Extraviado");
    private String descricao;

    TipoSituacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}