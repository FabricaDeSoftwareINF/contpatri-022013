package br.ufg.inf.es.fs.contpatri.mobile.gui.activity;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import br.ufg.inf.es.fs.contpatri.mobile.controller.InventarioControler;
import br.ufg.inf.es.fs.contpatri.mobile.util.FolderManager;

public final class SubLocaisActivity extends Activity {
	
	private transient AlertDialog.Builder builder;
	private transient List<String> groupList;
	private transient List<String> childList;
	private transient Map<String, List<String>> sublocalCollection;
	private transient ExpandableListView expListView;
	private transient ListView lista;
	private transient ArrayAdapter<File> aparelhosAdapter;
	private transient InventarioControler inventControl = new InventarioControler();
	private transient String caminho;

	@Override
	protected void onCreate(final Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.activity_tela_sub_locais);
		
		builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.acao);
		builder.setItems(R.array.acao_array,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							iniciaTelaColeta(caminho);
						} else if (which == 1) {
							inventControl.enviaColeta(caminho);
						} else if (which == 2) {
							iniciaTelaRelatorio();
						}

					}
				});

		final AlertDialog dialog = builder.create();
		dialog.show();
		
		aparelhosAdapter = new ArrayAdapter<File>(this, R.layout.message,
				FolderManager.getArquivos(Environment
						.getExternalStorageDirectory().toString(), ""));

		lista.setAdapter(aparelhosAdapter);
		lista.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adptView, View view, int pos,
					long arg) {

				caminho = aparelhosAdapter.getItem(pos).getPath();

				dialog.show();
			}
		});

	}

	public void sincronizarTudo(final View view) {
		
	}
	
	public void sincronizar(final View view) {

		AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {

			ProgressDialog dialog;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = ProgressDialog.show(SubLocaisActivity.this, "Aguarde",
						"Sincronizando, Por Favor Aguarde...");
			}

			@Override
			protected Void doInBackground(String... params) {
				String urlString = params[0];

				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(urlString);

				try {
					HttpResponse response = httpclient.execute(httpget);

					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream instream = entity.getContent();

						String str = jsonString(instream);

						JSONArray jsonA = new JSONArray(str);

						for (int i = 0; i < jsonA.length(); i++) {

							jsonA.getJSONObject(i).getJSONArray("inventario");
							File output = new File(""
									+ jsonA.getJSONObject(i).get("local")
											.toString());

							if (output.canWrite()) {
								BufferedWriter out = null;
								try {
									out = new BufferedWriter(new FileWriter(
											output, false));

									out.write(jsonA.toString());

								} catch (IOException e) {

								} finally {
									if (out != null) {
										try {
											out.close();
										} catch (IOException e) {

										}
									}
								}

							}
						}

						instream.close();

					}
				} catch (Exception e) {
					Log.e("DEVMEDIA", "Falha ao acessar Web service", e);
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				dialog.dismiss();
			}

		};
	}

	private void iniciaTelaColeta(String path) {
		Intent intent = new Intent(this, ColetaActivity.class);
		intent.putExtra("urijson", path);
		startActivity(intent);
		SubLocaisActivity.this.finish();
	}
	
	private void enviaColeta() {
		
	}

	private void iniciaTelaRelatorio() {
		
	}
	
	private String jsonString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

}
