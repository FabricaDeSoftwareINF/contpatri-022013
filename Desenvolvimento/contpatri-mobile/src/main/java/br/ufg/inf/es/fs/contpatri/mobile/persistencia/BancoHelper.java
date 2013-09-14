package br.ufg.inf.es.fs.contpatri.mobile.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class BancoHelper extends SQLiteOpenHelper {

	public static final String NOME_BANCO = "CONTPATRI";
	public static final int VERSAO_BANCO = 1;

	public static final String TABELA_TOMBAMENTO = "tombamento";

	public static final String TOMBAMENTO_CODIGO = "_id";
	public static final String TOMBAMENTO_SITUACAO = "situacao";
	public static final String TOMBAMENTO_SUBLOCAL = "sublocal";
	public static final String TOMBAMENTO_ALTERACAO = "alteracao";
	public static final String TOMBAMENTO_OBSERVACAO = "observacao";

	public static final String[] TOMBAMENTO_COLUNAS = new String[] {
			TOMBAMENTO_CODIGO, TOMBAMENTO_SITUACAO, TOMBAMENTO_SUBLOCAL,
			TOMBAMENTO_ALTERACAO, TOMBAMENTO_OBSERVACAO };

	private static final String CRIAR_TABELA_TOMBAMENTO = "CREATE TABLE "
			+ TABELA_TOMBAMENTO + " (" + TOMBAMENTO_CODIGO
			+ " INTEGER NOT NULL PRIMARY KEY," + TOMBAMENTO_SITUACAO
			+ " VARCHAR(30)," + TOMBAMENTO_SUBLOCAL + " VARCHAR(250),"
			+ TOMBAMENTO_ALTERACAO + " BIGINT," + TOMBAMENTO_OBSERVACAO
			+ " VARCHAR(250));";

	private static final String EXCLUIR_TABELA_TOMBAMENTO = "drop table "
			+ TABELA_TOMBAMENTO;

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
	}

	private static BancoHelper instancia = null;

	public static BancoHelper getInstance(final Context context) {
		if (instancia == null) {
			instancia = new BancoHelper(context);
		}
		return instancia;
	}

}
