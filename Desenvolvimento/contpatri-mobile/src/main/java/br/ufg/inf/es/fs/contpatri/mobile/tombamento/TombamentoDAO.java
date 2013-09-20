/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ 
 * Instituto de Informática UFG: http://www.inf.ufg.br 
 * Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013 
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
package br.ufg.inf.es.fs.contpatri.mobile.tombamento;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.persistencia.BancoHelper;

import com.google.gson.GsonBuilder;

/**
 * Classe que abstrai todas as funções CRUD do modelo <code>Tombamento</code> no
 * banco de dados do aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class TombamentoDAO {

	private final Context contexto;
	private SQLiteDatabase conexao;

	/**
	 * Construtor para instanciar a classe e inicializar as variáveis.
	 * 
	 * @param context
	 *            <code>Context</code> utilizado para realizar a conexão com o
	 *            banco de dados.
	 * @throws IllegalArgumentException
	 *             exceção lançada caso o contexto utilizado como parâmetro,
	 *             seja nulo
	 */
	public TombamentoDAO(final Context context) throws IllegalArgumentException {
		if (context == null) {
			throw new IllegalArgumentException(
					"É necessário um contexto para iniciar o banco de dados");
		} else {
			contexto = context;
		}
	}

	/**
	 * Método que retorna a conexão utilizada com o banco de dados, caso não
	 * haja conexão, retornará nulo ou um objeto sem conectividade com o banco.
	 * 
	 * @return retorna <code>SQLiteDatabase</code>
	 */
	public SQLiteDatabase getConexao() {
		return conexao;
	}

	/**
	 * Método que realiza a abertura de uma conexão com o banco de dados.
	 */
	public void abrirConexao() {

		if (conexao == null) {
			conexao = BancoHelper.getInstance(contexto).getWritableDatabase();
		} else {
			if (conexao.isOpen() == false) {
				conexao = BancoHelper.getInstance(contexto)
						.getWritableDatabase();
			} else {
				Log.w(TombamentoDAO.class.getSimpleName(), "Conexão já ativa");
			}
		}

	}

	/**
	 * Método que remove um elemento do tipo <code>Tombamento</code> na base de
	 * dados com base em seu código. Antes da remoção, é verificado se o mesmo
	 * existe. Se sim, é removido, senão, não é removido.
	 * 
	 * @param codigo
	 *            código que será especificado para ser verificado e removido
	 */
	public void deletar(final long codigo) {

		if (localizar(codigo) != null) {

			conexao.delete(BancoHelper.TABELA_TOMBAMENTO,
					BancoHelper.TOMBAMENTO_CODIGO + "=" + codigo, null);

		} else {
			Log.w(TombamentoDAO.class.getSimpleName(),
					"Não foi possível excluir pois não existe o código");
		}

	}

	/**
	 * Método que fecha a conexão com o banco de dados.
	 */
	public void fecharConexao() {
		if (conexao.isOpen()) {
			conexao.close();
		} else {
			Log.w(TombamentoDAO.class.getSimpleName(), "Conexão já fechada");
		}
	}

	/**
	 * Método que retorna todos os objetos do tipo <code>Tombamento</code> na
	 * base de dados. Caso não tenha nenhum, será retornada uma lista vazia.
	 * 
	 * @return retorna uma lista com elementos do tipo <code>Tombamento</code>
	 *         ou vazia, caso não tenha nenhuma informação
	 */
	public List<Tombamento> getTodos() {

		final List<Tombamento> lista = new ArrayList<Tombamento>();
		Tombamento tombamento;

		final Cursor cursor = conexao.query(BancoHelper.TABELA_TOMBAMENTO,
				BancoHelper.TOMBAMENTO_COLUNAS, null, null, null, null, null);

		cursor.moveToFirst();

		while (cursor.moveToNext()) {

			tombamento = new Tombamento();
			tombamento.setCodigo(cursor.getInt(0));
			tombamento.setSituacao(cursor.getString(1));
			tombamento.setSublocal(cursor.getString(2));
			tombamento.setUltimaAlteracao(cursor.getLong(3));
			tombamento.setObservacao(cursor.getString(4));

			lista.add(tombamento);

		}

		cursor.close();

		return lista;

	}

	/**
	 * Retorna todos os registros da base de dados e os colocará em uma
	 * <code>String</code> no formato JSON. Tal formato, irá ser tratado e irá
	 * ser identado conforme JSON.
	 * 
	 * @return retorna uma <code>String</code> que representa todos os objetos
	 *         da base de dados
	 */
	public String getTodosJson() {
		return new GsonBuilder().setPrettyPrinting().create()
				.toJson(getTodos().toArray());
	}

	/**
	 * Método de inserção de um objeto do tipo <code>Tombamento</code> na base
	 * de dados. Antes de ser inserido, será verificado se o mesmo existe, se
	 * sim, não irá inserir, caso contrário será inserido.
	 * 
	 * @param tombamento
	 *            <code>Tombamento</code> que será inserido na base de dados
	 */
	public void inserir(final Tombamento tombamento) {

		/*
		 * Verifica se o objeto já existe na base de dados. Se sim, não irá
		 * inserir, caso contrário, irá.
		 */
		if (localizar(tombamento.getCodigo()) == null) {

			final ContentValues valores = new ContentValues();
			valores.put(BancoHelper.TOMBAMENTO_CODIGO, tombamento.getCodigo());
			valores.put(BancoHelper.TOMBAMENTO_SITUACAO,
					tombamento.getSituacao());
			valores.put(BancoHelper.TOMBAMENTO_SUBLOCAL,
					tombamento.getSublocal());
			valores.put(BancoHelper.TOMBAMENTO_ALTERACAO,
					tombamento.getUltimaAlteracao());
			valores.put(BancoHelper.TOMBAMENTO_OBSERVACAO,
					tombamento.getObservacao());

			conexao.insert(BancoHelper.TABELA_TOMBAMENTO, null, valores);

		} else {
			alterar(tombamento);
		}

	}

	/**
	 * Método que realiza a alteração de um tombamento já existente na base de
	 * dados. A alteração é feita utilizando o código do tombamento como
	 * referência.
	 * 
	 * @param tombamento
	 *            novo registro do tombamento que será alterado na base de dados
	 */
	public void alterar(final Tombamento tombamento) {

		final ContentValues valores = new ContentValues();

		valores.put(BancoHelper.TOMBAMENTO_OBSERVACAO,
				tombamento.getObservacao());
		valores.put(BancoHelper.TOMBAMENTO_SITUACAO, tombamento.getSituacao());
		valores.put(BancoHelper.TOMBAMENTO_SUBLOCAL, tombamento.getSublocal());
		valores.put(BancoHelper.TOMBAMENTO_ALTERACAO,
				tombamento.getUltimaAlteracao());

		conexao.update(BancoHelper.TABELA_TOMBAMENTO, valores,
				BancoHelper.TOMBAMENTO_CODIGO + "=" + tombamento.getCodigo(),
				null);

	}

	/**
	 * Método que retorna um objeto do tipo <code>Tombamento</code> com base no
	 * código do tombamento do mesmo. Caso não seja encontrado nenhum registro,
	 * será retornado <b>null</b>.
	 * 
	 * @param codigo
	 *            código que será procurado na base de dados
	 * @return retorna <b>null</b>, caso não encontre nada, ou o objeto com seus
	 *         atributos completos caso ache
	 */
	public Tombamento localizar(final long codigo) {

		Tombamento tombamento = null;

		final Cursor cursor = conexao.query(BancoHelper.TABELA_TOMBAMENTO,
				BancoHelper.TOMBAMENTO_COLUNAS, BancoHelper.TOMBAMENTO_CODIGO
						+ "=" + codigo, null, null, null, null);

		/*
		 * Verifica se encontrou algum registro, se sim, irá atribuir os valores
		 * encontrados ao objeto Tombamento.
		 */
		if (cursor.getCount() != 0) {

			cursor.moveToFirst();

			tombamento = new Tombamento();
			tombamento.setCodigo(cursor.getInt(0));
			tombamento.setSituacao(cursor.getString(1));
			tombamento.setSublocal(cursor.getString(2));
			tombamento.setUltimaAlteracao(cursor.getLong(3));
			tombamento.setObservacao(cursor.getString(4));

		}

		cursor.close();

		return tombamento;

	}
}
