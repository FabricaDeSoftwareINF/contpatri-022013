/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.fs.contpatri.web.teste.dbunit;


import java.io.FileInputStream;
import javax.security.auth.login.Configuration;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Gustavo
 */
class DBunitTest extends DatabaseTestCase {

    private Session session;
    private IDatabaseConnection conn;
    private IDataSet dataSet;
    private FileInputStream loadFile;

    public DBunitTest() {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

        } catch (Exception e) {

            e.getMessage();

        }

    }

    @Before
    public void setUp() throws Exception {

//A cada execução dos testes limpa e insere

        getSetUpOperation();

    }

//Limpa tudo que tem nas tabelas e faz um insert
    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {

        return DatabaseOperation.CLEAN_INSERT;

    
}
    
@Override
protected IDatabaseConnection getConnection() throws Exception {

             conn = new DatabaseConnection(new Configuration().configure().buildSettings().getConnectionProvider().getConnection());

             return conn;

       }
     
       @Override

       protected IDataSet getDataSet() throws Exception {

             loadFile = new FileInputStream("src/test/java/DataAgente.xml");

             dataSet =  new  FlatXmlDataSet(loadFile);

             return dataSet;

       }
       
// Testes utilizando DBunit
       
       @Test
       public void testCheckLoginDataLoaded() throws Exception{

             assertNotNull(getDataSet());

             int rowCount = getDataSet().getTable(“Agente”).getRowCount();

             assertTrue(rowCount!=0);   }
       
      
      
}


