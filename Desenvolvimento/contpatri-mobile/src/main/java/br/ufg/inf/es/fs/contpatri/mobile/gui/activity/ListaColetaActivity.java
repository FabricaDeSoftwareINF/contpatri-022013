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

public final class ListaColetaActivity extends ListActivity {

	private transient ListaColetaAdapter lca;
	private transient List<Tombamento> listaTombamento;
	private transient Activity activity;
	private transient AlertDialog.Builder builder;
	private transient ListView lista;
	private transient Intent intent;
	private transient AlertDialog dialog;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_lista_coleta);

		activity = this;

		builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.acao);
		builder.setItems(R.array.acao_array,
				new DialogInterface.OnClickListener() {
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

		listaTombamento = Armazenamento.Externo.getListaTombamentos();

		NucleoApp.criaPastas();
		lista = getListView();
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(final AdapterView<?> adapter,
					final View view, final int pos, final long id) {
				AlertDialog.Builder builder;
				builder = new AlertDialog.Builder(activity);
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.setTitle("Excluir");
				builder.setMessage("Desejar excluir o tombamento "
						+ listaTombamento.get(pos).getCodigo() + " ?");

				builder.setPositiveButton("Sim",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									int which) {
								Armazenamento.Externo
										.excluirArquivoTombamento(listaTombamento
												.get(pos).getCodigo());
								lca.notifyDataSetChanged();
								dialog.dismiss();
							}
						});

				builder.setNegativeButton("Não",
						new DialogInterface.OnClickListener() {
							public void onClick(final DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				dialog = builder.create();
				dialog.setCanceledOnTouchOutside(true);
				dialog.show();
				return true;
			}
		});
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
		lca = new ListaColetaAdapter(this, listaTombamento);
		lista.setAdapter(lca);
	}

	public void sincronizar(final View view) {
		new EnviarColeta(activity, "http:\\", listaTombamento)
				.execute(new Void[0]);
	}

	public void menu(final View view) {
		final AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void iniciaTelaRelatorio() {

	}

}
