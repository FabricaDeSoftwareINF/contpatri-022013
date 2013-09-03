/*
 Esse documento é parte do código fonte e artefatos relacionados ao projeto
 CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.

 Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ Instituto de
 Informática UFG: http://www.inf.ufg.br Projeto CONTPATRI DROPBOX:
 https://www.dropbox.com/home/CONTPATRI%20-%20012013 Projeto CONTPATRI
 REDMINE:

 Copyleft © UFG.

 Licenciado sobre a licença GNU-GPL v3

 Você pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html

 A menos que especificado ou exigido por legislação local, o software é
 fornecido "da maneira que está", sem garantias ou condições de qualquer tipo,
 nem expressas nem implícitas. Em caso de dúvidas referir a licença GNU-GPL.
 */
package br.ufg.inf.es.fs.contpatri.web.controller;

import br.ufg.inf.es.fs.contpatri.model.BemPatrimonial;
import br.ufg.inf.es.fs.contpatri.persistencia.HibernateUtil;
import br.ufg.inf.es.fs.contpatri.persistencia.dao.BemPatrimonialDAO;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Guilherme de Paula
 */
public class BemPatrimonialDataModel extends LazyDataModel<BemPatrimonial> implements Serializable {

    private BemPatrimonialDAO bemPatrimonialDAO;

    /**
     *
     */
    public BemPatrimonialDataModel() {
        bemPatrimonialDAO = new BemPatrimonialDAO();
    }
    
    /**
     *
     * @param first primeiro elemento da lista
     * @param pageSize tamanho da página
     * @param sortField campo de ordenação
     * @param sortOrder ordem (decrescente, ascendente)
     * @param filters filtros
     * @return lista com os bens patrimoniais
     */
    @Override
    public List<BemPatrimonial> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, String> filters) {
        StringBuilder sb = new StringBuilder("from BemPatrimonial ");
        
        // Filtros
        if (!filters.isEmpty()) {
            sb.append("where ");
        }        
        for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
            String filterProperty = it.next();
            String filterValue = filters.get(filterProperty);
            sb.append(filterProperty);
            sb.append(" like ");
            sb.append('\'');
            sb.append(filterValue);
            sb.append("%");
            sb.append('\'').append(' ');
            if (it.hasNext()) {
                sb.append(" and ");
            }
        }
        
        // Ordenação
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                sb.append("order by ").append(sortField).append(" asc");
            } else if (sortOrder == SortOrder.DESCENDING) {
                sb.append("order by ").append(sortField).append(" desc");
            }
        }
        
        // Obtendo dados
        String hql = sb.toString();
        HibernateUtil.beginTransaction();
        Query query = HibernateUtil.getSession().createQuery(hql);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        List<BemPatrimonial> dados = bemPatrimonialDAO.findMany(query);
        HibernateUtil.commitTransaction();

        // Contagem das linhas na tabela
        HibernateUtil.beginTransaction();
        String hqlRowCount = "select count (*) ".concat(hql);
        query = HibernateUtil.getSession().createQuery(hqlRowCount);
        int rows = ((Long) query.list().get(0)).intValue();
        HibernateUtil.commitTransaction();  
        

        setRowCount(rows);

        return dados;
    }

    /**
     *
     * @param object
     * @return chave do bem patrimonial
     */
    @Override
    public Object getRowKey(BemPatrimonial object) {
        return object.getId();
    }
    
}
