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
package br.ufg.inf.es.fs.contpatri.mobile.gui.activity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.adapter.ListaColetaAdapter;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.TombamentoDAO;
import br.ufg.inf.es.fs.contpatri.mobile.webservice.EnviarColeta;

/**
 * Classe que exibe e gerencia a tela ListaColeta
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza Santos
 * 
 */
public final class ListaColetaActivity extends ListActivity {

	private TombamentoDAO tmbDAO;
	private ListaColetaAdapter lca;
	private Intent intent;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_lista_coleta);
		tmbDAO = new TombamentoDAO(this);

		final ListView lst = getListView();
		lst.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> adapter,
					final View view, final int pos, final long id) {
				excluirRegistro(pos);
				return true;
			}
		});

		lca = new ListaColetaAdapter(this);
		lst.setAdapter(lca);
	}

	@Override
	protected void onListItemClick(final ListView lst, final View view,
			final int posicao, final long id) {
		super.onListItemClick(lst, view, posicao, id);
		intent = new Intent(this, ColetaActivity.class);
		tmbDAO.abrirConexao();
		intent.putExtra("tombamento", tmbDAO.getTodos().get(posicao));
		tmbDAO.fecharConexao();
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		lca.notifyDataSetChanged();
	}

	/**
	 * Método que sincroniza as coletas dos ativos fixos do aplicativo, com o
	 * WebService.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void sincronizar(final View view) {
		new EnviarColeta(this).execute(new Void[0]);
		lca.notifyDataSetChanged();
	}

	/**
	 * Método que responde ao evento de clique e exibe o <code>Dialog</code>
	 * para escolha de uma opção.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void coletar(final View view) {
		tmbDAO.abrirConexao();
		Log.i("TESTE", tmbDAO.getTodosJson());
		tmbDAO.fecharConexao();
		intent = new Intent(this, ColetaActivity.class);
		startActivity(intent);
	}

	/**
	 * Método que exibe o <code>Dialog</code> quando o evento de clique longo é
	 * ativado.
	 * 
	 * @param posicao
	 *            posicao do item na lista que sofreu o evento
	 */
	private void excluirRegistro(final int posicao) {

		tmbDAO.abrirConexao();
		final Tombamento tombamento = tmbDAO.getTodos().get(posicao);
		tmbDAO.fecharConexao();

		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(this);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("Excluir");
		builder.setMessage("Desejar excluir o tombamento "
				+ tombamento.getCodigo() + " ?");

		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				tmbDAO.abrirConexao();
				tmbDAO.deletar(tombamento.getCodigo());
				tmbDAO.fecharConexao();
				lca.notifyDataSetChanged();
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				dialog.dismiss();
			}
		});

		final AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

}
