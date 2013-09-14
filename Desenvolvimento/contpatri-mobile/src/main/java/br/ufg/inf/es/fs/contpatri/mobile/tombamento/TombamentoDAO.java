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

public final class TombamentoDAO {

	private final Context contexto;
	private SQLiteDatabase conexao;

	public TombamentoDAO(final Context context) {
		contexto = context;
	}

	public void abrirConexao() {
		if (conexao == null || conexao.isOpen() == false) {
			conexao = BancoHelper.getInstance(contexto).getWritableDatabase();
		}
	}

	public void fecharConexao() {
		if (conexao.isOpen()) {
			conexao.close();
		}
	}

	public void inserir(final Tombamento tombamento) {

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
			Log.w(TombamentoDAO.class.getSimpleName(),
					"O registro já está no banco de dados");
		}

	}

	public void deletar(final long codigo) {

		if (localizar(codigo) != null) {

			conexao.delete(BancoHelper.TABELA_TOMBAMENTO,
					BancoHelper.TOMBAMENTO_CODIGO + "=" + codigo, null);

		} else {
			Log.w(TombamentoDAO.class.getSimpleName(),
					"Não foi possível excluir pois não existe o código");
		}

	}

	public Tombamento localizar(final long codigo) {

		Tombamento tombamento = null;

		final Cursor cursor = conexao.query(BancoHelper.TABELA_TOMBAMENTO,
				BancoHelper.TOMBAMENTO_COLUNAS, BancoHelper.TOMBAMENTO_CODIGO
						+ "=" + codigo, null, null, null, null);

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

	public String getTodosJson() {
		return new GsonBuilder().setPrettyPrinting().create()
				.toJson(getTodos().toArray());
	}
}
