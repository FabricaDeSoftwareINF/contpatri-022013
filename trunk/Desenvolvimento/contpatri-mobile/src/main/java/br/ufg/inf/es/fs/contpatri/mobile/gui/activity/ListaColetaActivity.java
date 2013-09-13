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

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.adapter.ListaColetaAdapter;
import br.ufg.inf.es.fs.contpatri.mobile.nucleo.NucleoApp;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.Armazenamento;
import br.ufg.inf.es.fs.contpatri.mobile.webservice.EnviarColeta;

/**
 * Classe que exibe e gerencia a tela ListaColeta
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ListaColetaActivity extends ListActivity {

	private ListaColetaAdapter lca;
	private List<Tombamento> listaTombamento;
	private Activity activity;
	private AlertDialog.Builder builder;
	private ListView lista;
	private Intent intent;
	private AlertDialog dialog;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_lista_coleta);
		NucleoApp.criaPastas();

		listaTombamento = Armazenamento.Externo.getListaTombamentos();

		lca = new ListaColetaAdapter(this, listaTombamento);
		activity = this;

		builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.acao);
		builder.setItems(R.array.acao_array,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							intent = new Intent(activity, ColetaActivity.class);
							startActivity(intent);
						} else if (which == 1) {
							iniciaTelaRelatorio();
						} else if (which == 2) {
							dialog.dismiss();
						}
					}
				});

		lista = getListView();
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> adapter,
					final View view, final int pos, final long id) {
				exibeDialogo(pos);
				return true;
			}
		});
		lista.setAdapter(lca);
	}

	@Override
	protected void onListItemClick(final ListView lista, final View view,
			final int posicao, final long id) {
		super.onListItemClick(lista, view, posicao, id);
		intent = new Intent(this, ColetaActivity.class);
		intent.putExtra("tombamento", listaTombamento.get(posicao));
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		menu(findViewById(R.id.btnMenu));
	}

	/**
	 * Método que sincroniza as coletas dos ativos fixos do aplicativo, com o
	 * WebService.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void sincronizar(final View view) {
		new EnviarColeta(activity, "http:\\", listaTombamento)
				.execute(new Void[0]);
	}

	/**
	 * Método que responde ao evento de clique e exibe o <code>Dialog</code>
	 * para escolha de uma opção.
	 * 
	 * @param view
	 *            view que realizou o evento de clique e chamou esse método
	 */
	public void menu(final View view) {
		final AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * Método que inicia a tela de Relatórios do aplicativo.
	 */
	private void iniciaTelaRelatorio() {

	}

	/**
	 * Método que exibe o <code>Dialog</code> quando o evento de clique longo é
	 * ativado.
	 * 
	 * @param posicao
	 *            posicao do item na lista que sofreu o evento
	 */
	private void exibeDialogo(final int posicao) {

		AlertDialog.Builder builder;
		builder = new AlertDialog.Builder(activity);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("Excluir");
		builder.setMessage("Desejar excluir o tombamento "
				+ listaTombamento.get(posicao).getCodigo() + " ?");

		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, int which) {
				Armazenamento.Externo.excluirArquivoTombamento(listaTombamento
						.get(posicao).getCodigo());
				dialog.dismiss();
				lca.notifyDataSetChanged();
			}
		});

		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

}
