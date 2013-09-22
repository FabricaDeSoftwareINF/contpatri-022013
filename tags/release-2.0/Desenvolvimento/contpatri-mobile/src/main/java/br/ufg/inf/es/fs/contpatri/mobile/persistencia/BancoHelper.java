package br.ufg.inf.es.fs.contpatri.mobile.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe que gerencia todas as informações relacionadas à estrutura do banco de
 * dados da aplicação. É nela que é criada as tabelas, recriação das mesmas em
 * caso de atualização e link de conexão com o arquivo do SQLite.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class BancoHelper extends SQLiteOpenHelper {

	/**
	 * Nome do banco que será utilizado pela aplicação.
	 */
	public static final String NOME_BANCO = "CONTPATRI";

	/**
	 * Versão atual do banco de dados.
	 */
	public static final int VERSAO_BANCO = 1;

	/**
	 * Tabela de tombamentos.
	 */
	public static final String TABELA_TOMBAMENTO = "tombamento";

	/**
	 * Código do tombamento.
	 */
	public static final String TOMBAMENTO_CODIGO = "_id";

	/**
	 * Situação do tombamento.
	 */
	public static final String TOMBAMENTO_SITUACAO = "situacao";

	/**
	 * Sublocal do tombamento.
	 */
	public static final String TOMBAMENTO_SUBLOCAL = "sublocal";

	/**
	 * Data de alteração do tombamento.
	 */
	public static final String TOMBAMENTO_ALTERACAO = "alteracao";

	/**
	 * Observação do tombamento.
	 */
	public static final String TOMBAMENTO_OBSERVACAO = "observacao";

	/**
	 * Relação de todas as colunas da tabela de tombamentos. Sua sequência segue
	 * a seguir: código, situação, sublocal, data de alteraçãoo e observação.
	 */
	public static final String[] TOMBAMENTO_COLUNAS = new String[] {
			TOMBAMENTO_CODIGO, TOMBAMENTO_SITUACAO, TOMBAMENTO_SUBLOCAL,
			TOMBAMENTO_ALTERACAO, TOMBAMENTO_OBSERVACAO };

	/**
	 * Query para criação da tabela de tombamentos.
	 */
	private static final String CRIAR_TABELA_TOMBAMENTO = "CREATE TABLE "
			+ TABELA_TOMBAMENTO + " (" + TOMBAMENTO_CODIGO
			+ " INTEGER NOT NULL PRIMARY KEY," + TOMBAMENTO_SITUACAO
			+ " VARCHAR(30)," + TOMBAMENTO_SUBLOCAL + " VARCHAR(250),"
			+ TOMBAMENTO_ALTERACAO + " BIGINT," + TOMBAMENTO_OBSERVACAO
			+ " VARCHAR(250));";

	/**
	 * Query para exclusão da tabela de tombamentos.
	 */
	private static final String EXCLUIR_TABELA_TOMBAMENTO = "drop table "
			+ TABELA_TOMBAMENTO;

	/**
	 * Variável estática dessa classe para permitir apenas uma instância da
	 * mesma. Vide singleton.
	 */
	private static BancoHelper instancia = null;

	/**
	 * Método público e estático para permitir apenas uma instância dessa
	 * classe. Vide singleton.
	 * 
	 * @param context
	 *            contexto utilizado para chamada do construtor pai dessa
	 *            classe.
	 * @return retorna a instância dessa classe
	 */
	public static BancoHelper getInstance(final Context context) {
		if (instancia == null) {
			instancia = new BancoHelper(context);
		}
		return instancia;
	}

	/**
	 * Construtor para instanciar a classe e permitir apenas uma instância da
	 * mesma. Vide singleton.
	 * 
	 * @param context
	 *            contexto para ser utilizada em uma chamada ao construtor pai
	 *            dessa classe
	 */
	private BancoHelper(final Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		db.execSQL(CRIAR_TABELA_TOMBAMENTO);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion) {
		db.execSQL(EXCLUIR_TABELA_TOMBAMENTO);
		onCreate(db);
	}

}
